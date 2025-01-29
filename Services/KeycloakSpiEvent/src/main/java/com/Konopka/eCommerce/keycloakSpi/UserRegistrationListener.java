package com.Konopka.eCommerce.keycloakSpi;

import com.Konopka.eCommerce.kafka.KafkaConfig;
import com.Konopka.eCommerce.kafka.KafkaUserDto;
import com.Konopka.eCommerce.kafka.UserDataMessageProducer;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

public class UserRegistrationListener implements EventListenerProvider {

    private final KafkaConfig kafkaConfig;
    private final UserDataMessageProducer producer;
    private final KeycloakSession session;

    public UserRegistrationListener(KeycloakSession session) {
        this.session = session;
        this.kafkaConfig = new KafkaConfig();
        this.producer = new UserDataMessageProducer(kafkaConfig.createProducer());
        kafkaConfig.createTopic("userData");
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType() == EventType.REGISTER) {
            String userId = event.getUserId();
            RealmModel realm = session.getContext().getRealm();
            UserModel user = session.users().getUserById(realm, userId);

            KafkaUserDto dto = new KafkaUserDto();
            dto.setUserId(userId);
            dto.setPhone(getAttribute(user, "phone"));
            dto.setCountry(getAttribute(user, "country"));
            dto.setCity(getAttribute(user, "city"));
            dto.setStreet(getAttribute(user, "street"));
            dto.setHouseNumber(getAttribute(user, "houseNumber"));
            dto.setFlatNumber(getAttribute(user, "flatNumber"));
            dto.setPostalCode(getAttribute(user, "postalCode"));

            producer.produceOrderMessage("userData", dto.toJson());
        }
    }

    // Helper method to safely get attributes
    private String getAttribute(UserModel user, String attributeName) {
        String attributeValue = user.getFirstAttribute(attributeName);
        return attributeValue != null ? attributeValue : "N/A";
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
    }

    @Override
    public void close() {
        producer.close();
    }
}

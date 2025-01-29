package com.Konopka.eCommerce.keycloakSpi;

import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class UserRegistrationListenerFactory implements EventListenerProviderFactory {

    @Override
    public EventListenerProvider create(KeycloakSession session) {
        return new UserRegistrationListener(session);
    }

    @Override
    public void init(org.keycloak.Config.Scope config) {
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
    }

    @Override
    public void close() {
    }

    @Override
    public String getId() {
        return "user-registration-listener";
    }
}

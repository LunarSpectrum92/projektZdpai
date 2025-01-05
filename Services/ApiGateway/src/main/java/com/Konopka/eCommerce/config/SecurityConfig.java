package com.Konopka.eCommerce.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
//@EnableWebFluxSecurity
public class SecurityConfig {



//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http.authorizeExchange(auth -> auth.anyExchange().authenticated())
//                .oauth2Login(withDefaults())
//                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
//        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
//        return http.build();
    //}
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//
//
//        http.authorizeExchange(auth -> auth
//                        .pathMatchers("/api/users/register").permitAll()
//                        .anyExchange().authenticated())
//                .oauth2Login(withDefaults())
//                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
//        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
//        return http.build();
//
//    }


//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        return new InMemoryClientRegistrationRepository(clientRegistration());
//    }
//
//    @Bean
//    public ClientRegistration clientRegistration() {
//        return ClientRegistration.withRegistrationId("keycloak")
//                .clientId("AuthService")
//                .clientSecret("tsbPJ9GEkGhnaY4wZG2yzV1PJuBqV2xJ")
//                .authorizationUri("http://localhost:7080/realms/eCommerce-realm/protocol/openid-connect/auth")
//                .tokenUri("http://localhost:7080/realms/eCommerce-realm/protocol/openid-connect/token")
//                .userInfoUri("http://localhost:7080/realms/eCommerce-realm/protocol/openid-connect/userinfo")
//                .jwkSetUri("http://localhost:7080/realms/eCommerce-realm/protocol/openid-connect/certs")
//                .scope("openid")
//                .redirectUri("http://localhost:9090/login/oauth2/code/keycloak")
//                .build();
//    }

//to
    //@Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .authorizeExchange(exchange -> exchange
//                        .pathMatchers("/api/**").authenticated()
//
//
//
//
//                       // .anyExchange().authenticated()
//                )
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
//        return http.build();
//    }

//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .authorizeExchange(auth -> auth.anyExchange().authenticated())
//                .oauth2Login(withDefaults())
//                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
//
//        return http.build();
//    }




//    @Bean
//    public InMemoryReactiveOAuth2AuthorizedClientService authorizedClientService(
//            ReactiveOAuth2AuthorizedClientRepository authorizedClientRepository) {
//        return new InMemoryReactiveOAuth2AuthorizedClientService(clientRegistrationRepository(), authorizedClientRepository);
//    }
//
//    @Bean
//    public ReactiveClientRegistrationRepository clientRegistrationRepository() {
//        // Definiujesz swoje klienty OAuth2 tutaj
//        return new InMemoryReactiveClientRegistrationRepository(clientRegistration());
//    }
//
//    @Bean
//    public ClientRegistration clientRegistration() {
//        // Przykład konfiguracji klienta OAuth2
//        return ClientRegistration.withRegistrationId("google")
//                .clientId("your-client-id")
//                .clientSecret("your-client-secret")
//                .scope("openid", "profile", "email")
//                .authorizationUri("https://accounts.google.com/o/oauth2/auth")
//                .tokenUri("https://oauth2.googleapis.com/token")
//                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
//                .build();
//    }


}
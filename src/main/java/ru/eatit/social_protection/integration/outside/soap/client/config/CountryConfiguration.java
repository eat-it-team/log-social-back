package ru.eatit.social_protection.integration.outside.soap.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import ru.eatit.social_protection.integration.outside.soap.client.PersonSoapConsumer;

@Configuration
public class CountryConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ru.eatit.social_protection.integration.outside.soap.client.generated");
        return marshaller;
    }

    @Bean
    public PersonSoapConsumer countryClient(Jaxb2Marshaller marshaller) {
      PersonSoapConsumer client = new PersonSoapConsumer();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
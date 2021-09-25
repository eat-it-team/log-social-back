package ru.eatit.social_protection.integration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import ru.redcom.lib.integration.api.client.dadata.DaDataClient;
import ru.redcom.lib.integration.api.client.dadata.DaDataClientFactory;

@Configuration
public class DadataConfig {

    @Value("${integration_service.dadata.apiKey}")
    private String apiKey;

    @Value("${integration_service.dadata.secretKey}")
    private String secretKey;

   @Bean
   public DaDataClient getDadataClient(){
       return DaDataClientFactory.getInstance(apiKey, secretKey);
   }

}

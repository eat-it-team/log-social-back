package ru.eatit.social_protection.integration.outside.soap.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ru.eatit.social_protection.integration.outside.soap.client.generated.GetCitizenRequest;
import ru.eatit.social_protection.integration.outside.soap.client.generated.GetCitizenResponse;

@Log4j2
public class PersonSoapConsumer extends WebServiceGatewaySupport {

    public GetCitizenResponse getCitizen(String snills) {
        GetCitizenRequest request = new GetCitizenRequest();
        request.setSnils(snills);
        GetCitizenResponse response = (GetCitizenResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/citizen", request);
        return response;
    }
}

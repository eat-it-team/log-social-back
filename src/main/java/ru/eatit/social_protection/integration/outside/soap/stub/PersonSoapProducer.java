package ru.eatit.social_protection.integration.outside.soap.stub;

import java.util.Optional;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.eatit.social_protection.integration.outside.domain.dto.PersonDto;
import ru.eatit.social_protection.integration.outside.domain.repository.PersonRepository;
import ru.eatit.social_protection.integration.outside.soap.stub.generated.Citizen;
import ru.eatit.social_protection.integration.outside.soap.stub.generated.GetCitizenRequest;
import ru.eatit.social_protection.integration.outside.soap.stub.generated.GetCitizenResponse;

@Endpoint
@RequiredArgsConstructor
public class PersonSoapProducer {
    private static final String NAMESPACE_URI = "https://github.com/eat-it-team";

    private final PersonRepository personRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCitizenRequest")
    @ResponsePayload
    public GetCitizenResponse getCitizen(@RequestPayload GetCitizenRequest getCitizenRequest) throws
                                                                                              DatatypeConfigurationException {
        GetCitizenResponse response = new GetCitizenResponse();
        response.setCitizen(toCitizen(personRepository.findById(getCitizenRequest.getSnils())));
        return response;
    }

    private Citizen toCitizen(Optional<PersonDto> personBox) throws DatatypeConfigurationException {
        if (personBox.isEmpty()) {
            return new Citizen();
        }
        Citizen citizen = new Citizen();
        PersonDto person = personBox.get();
        citizen.setBirthdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(person.getBirthDate().toString()));
        citizen.setFirstName(person.getFirstName());
        citizen.setInn(person.getInn());
        citizen.setLastName(person.getLastName());
        citizen.setSex(person.isMale());
        citizen.setSnils(person.getSnills());
        citizen.setSecondName(person.getSecondName());
        return citizen;
    }
}

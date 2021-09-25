package ru.eatit.integration.service.dadata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eatit.integration.service.dadata.entity.Geo;
import ru.redcom.lib.integration.api.client.dadata.DaDataClient;
import ru.redcom.lib.integration.api.client.dadata.dto.Address;
import ru.redcom.lib.integration.api.client.dadata.types.QcGeo;

import javax.annotation.PostConstruct;

@Service
public class DadataService {

    private final DaDataClient daDataClient;

    @Autowired
    public DadataService(DaDataClient daDataClient) {
        this.daDataClient = daDataClient;
    }

    @PostConstruct
    public void init() {
        //1 запрос = 10 копеек
        //Geo coordinates = getCoordinates("Димитровград, ул.Славского, д.10, кв. 5");
        //System.out.println(coordinates);
    }

    public Geo getCoordinates(String address) {
        try {
            Address cleanAddress = daDataClient.cleanAddress(address);
            if (cleanAddress == null || QcGeo.UNDEFINED.equals(cleanAddress.getQcGeo())) {
                return null;
            }
            return new Geo(cleanAddress.getGeoLat(), cleanAddress.getGeoLon());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.engagetech.clients;

import com.engagetech.exceptions.RestResponseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient implements Client {

    private static final String MISSING_RESPONSE_ENTITY = "Missing Response Entity";
    private static final String MISSING_RESPONSE_BODY = "Missing Response Body";

    private RestTemplate restTemplate;

    public RestClient() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public <T> T sendGetRequest(String destinationUrl, Class<T> classType) {
        if (classType == null) throw new IllegalArgumentException();

        final ResponseEntity<T> responseEntity = restTemplate.getForEntity(destinationUrl, classType);

        if (responseEntity == null) throw new RestResponseException(MISSING_RESPONSE_ENTITY);
        if (!responseEntity.hasBody()) throw new RestResponseException(MISSING_RESPONSE_BODY);

        return responseEntity.getBody();
    }
}
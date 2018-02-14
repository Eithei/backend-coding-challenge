package com.engagetech.utilities;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TestRestRequestHelper {
    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    public <T> ResponseEntity sendGetRequest(String destinationUrl, Class<T> classType) {
        return testRestTemplate.getForEntity(destinationUrl, classType);
    }

    public <T> ResponseEntity sendPostRequest(String url, T entity, Class<T> classType) {
        final HttpEntity httpEntity = getHttpEntity(entity);
        return testRestTemplate.postForEntity(url, httpEntity, classType);
    }

    private <T> HttpEntity getHttpEntity(T entity){
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new HttpEntity<>(entity, headers);
    }
}

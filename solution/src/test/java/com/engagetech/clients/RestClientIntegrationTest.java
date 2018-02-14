package com.engagetech.clients;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
public class RestClientIntegrationTest {
    private static final String CORRECT_URI = "http://www.google.com";
    private static final String INCORRECT_URI = "http://randomuri.co.uk";
    private static final String RANDOM_STRING = "Random URI";
    private Client restClient;

    @Before
    public void setup() {
        restClient = new RestClient();
    }

    @Test
    public void testSendGetRequest_WhenParamsAreCorrect_ShouldReturnCorrectResponse() {
        final String response = restClient.sendGetRequest(CORRECT_URI, String.class);
        assertNotNull(response);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSendGetRequest_WhenUriIsNull_ShouldThrowException() {
        restClient.sendGetRequest(null, String.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSendGetRequest_WhenClassIsNull_ShouldThrowException() {
        restClient.sendGetRequest(CORRECT_URI, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSendGetRequest_WhenRandomStringIsSupplied_ShouldThrowException() {
        restClient.sendGetRequest(RANDOM_STRING, String.class);
    }

    @Test(expected = ResourceAccessException.class)
    public void testSendGetRequest_WhenRandomUriIsSupplied_ShouldThrowException() {
        restClient.sendGetRequest(INCORRECT_URI, String.class);
    }

    @Test(expected = RestClientException.class)
    public void testSendGetRequest_WhenParamsAreIncompatible_ShouldThrowException() {
        restClient.sendGetRequest(CORRECT_URI, Integer.class);
    }
}

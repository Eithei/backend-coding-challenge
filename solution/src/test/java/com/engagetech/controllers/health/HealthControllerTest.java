package com.engagetech.controllers.health;

import com.engagetech.utilities.TestRestRequestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HealthControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestRequestHelper testRestRequestHelper;

    private static final String HEALTH_URL = "http://localhost:%d/app/health";

    @Test
    public void testGetHealthStatus_WhenAppIsUp_ShouldReturnOK() {
        final String targetUrl = String.format(HEALTH_URL, port);
        final ResponseEntity<String> response = testRestRequestHelper.sendGetRequest(targetUrl, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}

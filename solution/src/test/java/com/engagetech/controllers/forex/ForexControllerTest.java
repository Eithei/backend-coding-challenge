package com.engagetech.controllers.forex;

import com.engagetech.services.forex.FixerIOService;
import com.engagetech.utilities.TestRestRequestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static com.engagetech.entities.forex.Currency.EUR;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ForexControllerTest {
    @MockBean
    private FixerIOService fixerIOService;
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestRequestHelper testRestRequestHelper;
    private static final String CURRENCIES_URL = "http://localhost:%d/app/currencies?currency=%s";

    @Before
    public void setup() {
        final BigDecimal rate = new BigDecimal(1.2235);

        when(fixerIOService.getCurrencyExchangeRateAgainstGBP(any())).thenReturn(rate);
    }

    @Test
    public void testGetCurrencyExchangeRateAgainstGBP_WhenCorrectCurrencyIsPassed_ShouldReturnRate() {
        final String UrlWithCurrency = String.format(CURRENCIES_URL, port, EUR);
        final ResponseEntity response = testRestRequestHelper.sendGetRequest(UrlWithCurrency, BigDecimal.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        final BigDecimal rate = (BigDecimal) response.getBody();
        assertNotNull(rate);
    }

    @Test
    public void testGetCurrencyExchangeRateAgainstGBP_WhenUnrecognizedCurrencyIsPassed_ShouldReturnBadRequest() {
        final String UrlWithCurrency = String.format(CURRENCIES_URL, port, "BGN");
        final ResponseEntity response = testRestRequestHelper.sendGetRequest(UrlWithCurrency, Object.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetCurrencyExchangeRateAgainstGBP_WhenCurrencyIsNotPassed_ShouldReturnBadRequest() {
        final String UrlWithoutCurrency = String.format(CURRENCIES_URL, port, null);
        final ResponseEntity response = testRestRequestHelper.sendGetRequest(UrlWithoutCurrency, Object.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}

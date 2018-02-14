package com.engagetech.clients.fixerio;

import com.engagetech.clients.RestClient;
import com.engagetech.entities.forex.FixerIOResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.engagetech.entities.forex.Currency.EUR;
import static com.engagetech.entities.forex.Currency.GBP;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class FixerIORestClientIntegrationTest {
    private FixerIOClient fixerIOClient;

    @Before
    public void setup() {
        final RestClient restClient = new RestClient();
        fixerIOClient = new FixerIORestClient(restClient);
    }

    @Test
    public void testGetExchangeRateBetweenCurrencies_WhenBothCurrenciesAreProvided_ShouldReturnFullResponse() {
        final FixerIOResponse response = fixerIOClient.getExchangeRateBetweenCurrencies(GBP, EUR);
        assertNotNull(response);
        assertFalse(response.getRates().isEmpty());
    }

    @Test
    public void testGetExchangeRateBetweenCurrencies_WhenBaseCurrencyIsProvided_ShouldReturnRangeOfRates() {
        final FixerIOResponse response = fixerIOClient.getExchangeRateBetweenCurrencies(GBP, null);
        assertNotNull(response);
        assertTrue(response.getRates().isEmpty());
    }

    @Test
    public void testGetExchangeRateBetweenCurrencies_WhenQuotedCurrencyIsProvided_ShouldReturnRatesAgainstTheDefaultEUR() {
        final FixerIOResponse response = fixerIOClient.getExchangeRateBetweenCurrencies(null, EUR);
        assertNotNull(response);
        assertEquals(EUR, response.getBase());
        assertTrue(response.getRates().isEmpty());
    }
}

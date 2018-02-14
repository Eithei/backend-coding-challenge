package com.engagetech.services.forex;

import com.engagetech.clients.fixerio.FixerIORestClient;
import com.engagetech.entities.forex.FixerIOResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.engagetech.entities.forex.Currency.EUR;
import static com.engagetech.entities.forex.Currency.GBP;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ForexServiceTest {

    @Mock
    private FixerIORestClient fixerIORestClient;

    private ForexService forexService;

    @Before
    public void setup() {
        forexService = new FixerIOService(fixerIORestClient);

        final Map<String, BigDecimal> rates = new HashMap<>();
        rates.put(EUR.toString(), new BigDecimal(1.2263));

        final FixerIOResponse response = FixerIOResponse.builder()
                .rates(rates)
                .build();

        when(fixerIORestClient.getExchangeRateBetweenCurrencies(GBP, EUR)).thenReturn(response);
    }

    @Test
    public void testGetCurrencyExchangeRateAgainstGBP_WhenQuotedCurrencySpecified_ShouldReturnRate() {
        final BigDecimal rate = forexService.getCurrencyExchangeRateAgainstGBP(EUR);
        assertNotNull(rate);
    }

    @Test
    public void testGetCurrencyExchangeRateAgainstGBP_WhenQuotedCurrencyIsSameAsBase_ShouldReturnRateOfOne() {
        final BigDecimal rate = forexService.getCurrencyExchangeRateAgainstGBP(GBP);
        assertEquals(new BigDecimal(1), rate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCurrencyExchangeRateAgainstGBP_WhenQuotedCurrencyIsNull_ShouldThrowException() {
        forexService.getCurrencyExchangeRateAgainstGBP(null);
    }
}

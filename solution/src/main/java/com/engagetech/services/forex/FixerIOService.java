package com.engagetech.services.forex;

import com.engagetech.clients.fixerio.FixerIOClient;
import com.engagetech.clients.fixerio.FixerIORestClient;
import com.engagetech.entities.forex.Currency;
import com.engagetech.entities.forex.FixerIOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.engagetech.entities.forex.Currency.GBP;

@Service
public class FixerIOService implements ForexService {

    private static final Currency BASE_CURRENCY = GBP;
    private static final int SAME_CURRENCY_RATE = 1;

    private FixerIOClient fixerIOClient;

    @Autowired
    public FixerIOService(FixerIORestClient fixerIOClient) {
        this.fixerIOClient = fixerIOClient;
    }

    public BigDecimal getCurrencyExchangeRateAgainstGBP(Currency currency) {
        if (currency == null) throw new IllegalArgumentException();
        if (currency.equals(GBP)) return new BigDecimal(SAME_CURRENCY_RATE);

        final FixerIOResponse response = fixerIOClient.getExchangeRateBetweenCurrencies(BASE_CURRENCY, currency);
        return response.getRates().get(currency.toString());
    }
}

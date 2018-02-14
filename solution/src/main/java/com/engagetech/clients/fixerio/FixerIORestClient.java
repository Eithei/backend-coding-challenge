package com.engagetech.clients.fixerio;

import com.engagetech.clients.Client;
import com.engagetech.clients.RestClient;
import com.engagetech.entities.forex.Currency;
import com.engagetech.entities.forex.FixerIOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.engagetech.entities.forex.Currency.EMPTY_CURRENCY_STRING;
import static com.engagetech.entities.forex.Currency.EUR;

@Component
public class FixerIORestClient implements FixerIOClient {

    private static final String URL = "https://api.fixer.io/latest?base=%s&symbols=%s";
    private Client client;

    @Autowired
    public FixerIORestClient(RestClient client) {
        this.client = client;
    }

    /**
     * Standard format for foreign exchange currency pairs is EUR/GBP.
     * @param base The first currency in a pair (EUR in EUR/GBP).
     * @param quoted The second currency in a pair (GBP in EUR/GBP).
     * @return Response object containing the base currency and the associated conversion rates.
     */
    public FixerIOResponse getExchangeRateBetweenCurrencies(Currency base, Currency quoted) {
        if (base == null) base = EUR; //FixerIOs default behavior is to query against EUR.
        if (quoted == null) quoted = EMPTY_CURRENCY_STRING;
        final String targetUrl = String.format(URL, base.toString(), quoted.toString());
        return client.sendGetRequest(targetUrl, FixerIOResponse.class);
    }
}

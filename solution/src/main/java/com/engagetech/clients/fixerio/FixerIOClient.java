package com.engagetech.clients.fixerio;

import com.engagetech.entities.forex.Currency;
import com.engagetech.entities.forex.FixerIOResponse;

/**
 * FixerIO is a foreign exchange rates and currency conversion API.
 * URL: http://fixer.io
 * Available on: 14.02.2018
 */
public interface FixerIOClient {
    FixerIOResponse getExchangeRateBetweenCurrencies(Currency base, Currency quoted);
}

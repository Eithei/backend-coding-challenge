package com.engagetech.services.forex;

import com.engagetech.entities.forex.Currency;

import java.math.BigDecimal;

public interface ForexService {
    BigDecimal getCurrencyExchangeRateAgainstGBP(Currency quoted);
}

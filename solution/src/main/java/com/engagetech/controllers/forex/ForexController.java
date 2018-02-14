package com.engagetech.controllers.forex;

import com.engagetech.entities.forex.Currency;
import com.engagetech.services.forex.ForexService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@Api(name = "Forex Controller", description = "Retrieving currency exchange rates", visibility = ApiVisibility.PUBLIC)
@RestController
@RequestMapping(value = "app/currencies")
public class ForexController {
    private ForexService forexService;

    @Autowired
    public ForexController(ForexService forexService) {
        this.forexService = forexService;
    }

    @ApiMethod(description = "Retrieves exchange rate of currency against GBP.", visibility = ApiVisibility.PUBLIC)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<BigDecimal> getCurrencyExchangeRateAgainstGBP(
            @ApiQueryParam(name = "currency", description = "Quoted currency against the GBP.")
            @RequestParam Currency currency) {
        final BigDecimal exchangeRate = forexService.getCurrencyExchangeRateAgainstGBP(currency);
        return new ResponseEntity<>(exchangeRate, HttpStatus.OK);
    }
}

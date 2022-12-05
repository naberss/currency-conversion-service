package com.naberss.currencyconversionservice.controller;

import com.naberss.currencyconversionservice.DTO.CurrencyConversion;
import com.naberss.currencyconversionservice.feign.CurrencyExchangeProxy;
import com.naberss.currencyconversionservice.feign.CurrencyExchangeProxyWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    CurrencyExchangeProxy currencyExchangeProxy;

    @Autowired
    CurrencyExchangeProxyWrapper currencyExchangeProxyWrapper;

    @GetMapping(value = "/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversion> currencyConversionResponseEntity = new RestTemplate().getForEntity(
                "http://localhost:8001/currency-exchange/from/USD/to/INR",
                CurrencyConversion.class,
                uriVariables);

        CurrencyConversion currencyConversion = currencyConversionResponseEntity.getBody();

        return new CurrencyConversion(currencyConversion.getId(),
                currencyConversion.getFrom(),
                currencyConversion.getTo(),
                currencyConversion.getConversionMultiple(),
                quantity,
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment());
    }

    @GetMapping(value = "/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionaFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        System.out.println("im here");
        CurrencyConversion currencyConversion = currencyExchangeProxyWrapper.retrieveExchangeValue(from, to);
        System.out.println("im here");

        return new CurrencyConversion(currencyConversion.getId(),
                currencyConversion.getFrom(),
                currencyConversion.getTo(),
                currencyConversion.getConversionMultiple(),
                quantity,
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment());
    }
}

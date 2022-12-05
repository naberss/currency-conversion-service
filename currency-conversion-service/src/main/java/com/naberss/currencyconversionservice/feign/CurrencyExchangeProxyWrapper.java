package com.naberss.currencyconversionservice.feign;

import com.naberss.currencyconversionservice.DTO.CurrencyConversion;
import com.naberss.currencyconversionservice.exception.IgnoredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeProxyWrapper {

    @Autowired
    CurrencyExchangeProxy currencyExchangeProxy;

    public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
        try{
            return currencyExchangeProxy.retrieveExchangeValue(from, to);
        }catch(IgnoredException e){
            return new CurrencyConversion();
        }
    };

}

package com.naberss.currencyconversionservice.feign;

import com.naberss.currencyconversionservice.DTO.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "currency-exchange",url = "localhost:8001")
public interface CurrencyExchangeProxy {
    @GetMapping(value = "/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from,@PathVariable String to);
}



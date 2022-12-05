package com.naberss.currencyconversionservice.config;

import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class CurrencyExchangeClientConfig {

    @Value("${currency.exchange.retryStartingIntervalMillis}")
    private long retryStartingIntervalMillis;

    @Value("${currency.exchange.retryMaxIntervalMillis}")
    private long retryMaxIntervalMillis;

    @Value("${currency.exchange.retryMaxAttempts}")
    private int retryMaxAttempts;

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(retryStartingIntervalMillis, retryMaxIntervalMillis, retryMaxAttempts);
    }

    @Bean
    public ErrorDecoder clientErrorDecoder(){
        return new currencyExchangeErrorDecoder();
    };

}

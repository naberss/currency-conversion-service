package com.naberss.currencyconversionservice.config;

import com.naberss.currencyconversionservice.exception.IgnoredException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class currencyExchangeErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {

        if (response.status() >= 400 && response.status() <= 599) {
            return new IgnoredException("Business-logic Exception Ignored");
        }

        return new Exception();
    }
}

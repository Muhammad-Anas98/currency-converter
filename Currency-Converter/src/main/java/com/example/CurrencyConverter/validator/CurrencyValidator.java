package com.example.CurrencyConverter.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.Set;

@Component
@Slf4j
public class CurrencyValidator {

    public void validateCurrencyCode(String sourceCurrency, String targetCurrency)
    {
        log.info("validating currency code.");
        Set<Currency> currencies = Currency.getAvailableCurrencies();
        if(!currencies.contains(Currency.getInstance(targetCurrency)))
        {
            throw new IllegalArgumentException("Your entered currency "+targetCurrency+" is not correct. Please try again.");
        }
        if(!currencies.contains(Currency.getInstance(sourceCurrency)))
        {
            throw new IllegalArgumentException("Your entered currency "+sourceCurrency+" is not correct. Please try again.");
        }
    }
}

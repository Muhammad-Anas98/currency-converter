package com.example.CurrencyConverter.validator;

import com.example.CurrencyConverter.exception.ApplicationException;
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
        try {
            //try-catch is used here to handle the exception occurred by the inbuilt java Currency.getInstance method as we
            //are trying to get instance and if it did not get it throws exception.
            if (!currencies.contains(Currency.getInstance(targetCurrency)) || !currencies.contains(Currency.getInstance(sourceCurrency))) {
                throw new ApplicationException("Your entered currency code is not correct. Please try again.");
            }
        }catch (Exception e)
        {
            throw new ApplicationException("Your entered currency code is not correct. Please try again.");
        }
    }
}

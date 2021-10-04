package com.example.CurrencyConverter.service;

import com.example.CurrencyConverter.adapter.CurrencyAdapter;
import com.example.CurrencyConverter.dtos.CurrencyResponseDto;
import com.example.CurrencyConverter.dtos.ExchangeRateResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Set;

@Slf4j
@Service
public class CurrencyService {

    @Autowired
    private CurrencyAdapter currencyAdapter;

    public CurrencyResponseDto exchangeRateConverter (String sourceCurrency, String targetCurrency, BigDecimal amount)
    {
        ExchangeRateResponseDto exchangeRateResponseDto = currencyAdapter.callExConvert(sourceCurrency,targetCurrency);

        BigDecimal sourceCurrencyValue = exchangeRateResponseDto.getRates().get(sourceCurrency);
        BigDecimal targetCurrencyValue = exchangeRateResponseDto.getRates().get(targetCurrency);
        BigDecimal targetedAmount = targetCurrencyValue.divide(sourceCurrencyValue,6, RoundingMode.HALF_EVEN).multiply(amount);

        return  CurrencyResponseDto.builder().targetedAmount(targetedAmount).build();
    }

}

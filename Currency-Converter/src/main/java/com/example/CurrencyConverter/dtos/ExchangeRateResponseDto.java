package com.example.CurrencyConverter.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateResponseDto {
    private Boolean success;
    private String timestamp;
    private String base;
    private LinkedHashMap<String,BigDecimal> rates;
    private String date;
}

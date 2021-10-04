package com.example.CurrencyConverter.controller.v1;

import com.example.CurrencyConverter.dtos.CurrencyResponseDto;
import com.example.CurrencyConverter.info.ApiResponse;
import com.example.CurrencyConverter.info.ErrorInfo;
import com.example.CurrencyConverter.service.CurrencyService;
import com.example.CurrencyConverter.validator.CurrencyValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;


@Slf4j
@RestController
@RequestMapping("/v1")
public class CurrencyConverterController {

    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private CurrencyValidator currencyValidator;

    @GetMapping("/currency/convert")
    public ResponseEntity<ApiResponse<CurrencyResponseDto>> getConvertedValue (@RequestParam("source") String sourceCurrency,
                                                                               @RequestParam("target") String targetCurrency,
                                                                               @RequestParam("amount") BigDecimal amount)
    {
        StopWatch watch = new StopWatch();
        watch.start();
        currencyValidator.validateCurrencyCode(sourceCurrency,targetCurrency);
        log.info("Going to calculate target currency value");
        CurrencyResponseDto currencyResponseDto = currencyService.exchangeRateConverter(sourceCurrency,targetCurrency,amount);
        watch.stop();
        return ResponseEntity.ok(new ApiResponse<>(null, currencyResponseDto,watch.getTotalTimeSeconds()));
    }
}

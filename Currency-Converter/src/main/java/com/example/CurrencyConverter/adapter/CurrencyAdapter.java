package com.example.CurrencyConverter.adapter;

import com.example.CurrencyConverter.dtos.ExchangeRateResponseDto;
import com.example.CurrencyConverter.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.example.CurrencyConverter.util.Constants.*;

@Slf4j
@Component
public class CurrencyAdapter {

    @Autowired
    private RestTemplate restTemplate;

    public ExchangeRateResponseDto callExConvert(String sourceCurrency, String targetCurrency)
    {
        final String url = EXCHANGE_RATE_BASE_URL.concat(LATEST_ENDPOINT);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("access_key", API_KEY)
                .queryParam("symbols", sourceCurrency+","+targetCurrency)
                .queryParam("format",1);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        try {
            log.info("calling url: {} ", url);
            HttpEntity<ExchangeRateResponseDto> response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    ExchangeRateResponseDto.class);

            return response.getBody();
        } catch (Exception e) {
            log.info("Something bad encountered {}",e.getMessage());
            throw new ApplicationException("Error encountered while fetching latest currency values");
        }
    }
}

package com.example.CurrencyConverter.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ApiResponse<T> {

    private List<ErrorInfo> errors;
    private T data;
    private Double elapsedTimeInSec;
}

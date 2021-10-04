package com.example.CurrencyConverter.exception;


import com.example.CurrencyConverter.info.ApiResponse;
import com.example.CurrencyConverter.info.ErrorInfo;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity<ApiResponse<Void>> handleApplicationException(ApplicationException e) {
        ApiResponse<Void> response = new ApiResponse<Void>(new ArrayList<>(), null,null);
        response.getErrors().add(new ErrorInfo(e.getMessage()));
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiResponse<Void>> handleException(ApplicationException e) {
        ApiResponse<Void> response = new ApiResponse<Void>(new ArrayList<>(), null,null);
        response.getErrors().add(new ErrorInfo(e.getMessage()));
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
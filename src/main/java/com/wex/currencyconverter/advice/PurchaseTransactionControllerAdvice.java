package com.wex.currencyconverter.advice;

import com.wex.currencyconverter.dto.ErrorDTO;
import com.wex.currencyconverter.exception.NoCurrencyConvertionRateAvailableException;
import com.wex.currencyconverter.exception.PurchaseTransactionNotFoundException;
import com.wex.currencyconverter.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice(basePackages = "com.wex.currencyconverter.controller")
public class PurchaseTransactionControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorDTO handleValidationException(ValidationException ex) {
        return ErrorDTO.builder()
                .message("Invalid resource data")
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoCurrencyConvertionRateAvailableException.class)
    public ErrorDTO handleCurrencyConversionRateException(NoCurrencyConvertionRateAvailableException ex) {
        return ErrorDTO.builder()
                .message("The purchase cannot be converted to the target currency.")
                .status(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PurchaseTransactionNotFoundException.class)
    public ErrorDTO handleCurrencyConversionRateException(PurchaseTransactionNotFoundException ex) {
        return ErrorDTO.builder()
                .message("The purchase transaction cannot be found!")
                .status(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
    }
}

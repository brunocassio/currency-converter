package com.wex.currencyconverter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConvertedAmountDTO {

    private String countryCurrencyDesc;
    private BigDecimal exchangeRate;
    private BigDecimal convertedAmount;
}

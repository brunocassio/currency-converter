package com.wex.currencyconverter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConvertedAmountDTO {

    private String countryCurrencyDesc;
    private Double exchangeRate;
    private Double convertedAmount;
}

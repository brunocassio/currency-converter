package com.wex.currencyconverter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConvertedPurchaseTransactionDTO {

    private long id;
    private String description;
    private LocalDate transactionDate;
    private Double usDollarAmount;

    private List<ConvertedAmountDTO> convertedAmounts;

}

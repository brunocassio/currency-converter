package com.wex.currencyconverter.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PurchaseTransactionDTO {

    private String description;

    private LocalDate transactionDate;

    private Double purchaseAmount;

}

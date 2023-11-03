package com.wex.currencyconverter.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseTransactionDTO {

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate transactionDate;

    private BigDecimal purchaseAmount;

}

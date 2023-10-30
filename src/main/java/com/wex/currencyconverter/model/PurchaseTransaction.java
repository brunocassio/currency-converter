package com.wex.currencyconverter.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@Entity
public class PurchaseTransaction {

    @Id
    private long id;
    private String description;
    private LocalDate transactionDate;
    private Double purchaseAmount;


}

package com.wex.currencyconverter.converter;

import com.wex.currencyconverter.dto.PurchaseTransactionDTO;
import com.wex.currencyconverter.model.PurchaseTransaction;

public class PurchaseTransactionConverter {

    public static PurchaseTransaction convertToEntity(PurchaseTransactionDTO dto) {
        return PurchaseTransaction.builder()
                .description(dto.getDescription())
                .transactionDate(dto.getTransactionDate())
                .purchaseAmount(dto.getPurchaseAmount())
                .build();
    }

    public static PurchaseTransactionDTO convertToDTO(PurchaseTransaction entity) {
        return PurchaseTransactionDTO.builder()
                .description(entity.getDescription())
                .transactionDate(entity.getTransactionDate())
                .purchaseAmount(entity.getPurchaseAmount())
                .build();
    }
}

package com.wex.currencyconverter.service;

import com.wex.currencyconverter.converter.PurchaseTransactionConverter;
import com.wex.currencyconverter.dto.PurchaseTransactionDTO;
import com.wex.currencyconverter.model.PurchaseTransaction;
import com.wex.currencyconverter.repository.PurchaseTransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseTransactionServiceImpl implements PurchaseTransactionService{

    private PurchaseTransactionRepository purchaseTransactionRepository;

    public PurchaseTransactionServiceImpl(PurchaseTransactionRepository purchaseTransactionRepository) {
        this.purchaseTransactionRepository = purchaseTransactionRepository;
    }

    @Override
    public PurchaseTransactionDTO insert(PurchaseTransactionDTO purchaseTransactionDTO) {
        PurchaseTransaction purchaseTransaction = PurchaseTransactionConverter.convertToEntity(purchaseTransactionDTO);
        return PurchaseTransactionConverter.convertToDTO(purchaseTransactionRepository.save(purchaseTransaction));
    }
}

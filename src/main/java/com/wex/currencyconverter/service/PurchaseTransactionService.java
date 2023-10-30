package com.wex.currencyconverter.service;

import com.wex.currencyconverter.dto.PurchaseTransactionDTO;
import org.springframework.stereotype.Service;

@Service
public interface PurchaseTransactionService {

    PurchaseTransactionDTO insert(PurchaseTransactionDTO purchaseTransactionDTO);
}

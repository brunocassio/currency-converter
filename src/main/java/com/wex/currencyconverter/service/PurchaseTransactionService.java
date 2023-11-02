package com.wex.currencyconverter.service;

import com.wex.currencyconverter.dto.ConvertedPurchaseTransactionDTO;
import com.wex.currencyconverter.dto.PurchaseTransactionDTO;
import com.wex.currencyconverter.model.PurchaseTransaction;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchaseTransactionService {

    PurchaseTransaction insert(PurchaseTransactionDTO purchaseTransactionDTO);

    List<ConvertedPurchaseTransactionDTO> retrieve(Pageable pageable, Long id);
}

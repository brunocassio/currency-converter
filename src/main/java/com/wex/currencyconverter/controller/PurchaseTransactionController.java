package com.wex.currencyconverter.controller;

import com.wex.currencyconverter.dto.PurchaseTransactionDTO;
import com.wex.currencyconverter.service.PurchaseTransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase-transaction")
public class PurchaseTransactionController {

    private final PurchaseTransactionService purchaseTransactionService;

    public PurchaseTransactionController(PurchaseTransactionService purchaseTransactionService) {
        this.purchaseTransactionService = purchaseTransactionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseTransactionDTO insert(@RequestBody PurchaseTransactionDTO dto) {
        return purchaseTransactionService.insert(dto);
    }
}

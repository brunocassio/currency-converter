package com.wex.currencyconverter.controller;

import com.wex.currencyconverter.dto.ConvertedPurchaseTransactionDTO;
import com.wex.currencyconverter.dto.PurchaseTransactionDTO;
import com.wex.currencyconverter.exception.ValidationException;
import com.wex.currencyconverter.model.PurchaseTransaction;
import com.wex.currencyconverter.service.PurchaseTransactionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/purchase-transaction")
public class PurchaseTransactionController {

    private final PurchaseTransactionService purchaseTransactionService;

    public PurchaseTransactionController(PurchaseTransactionService purchaseTransactionService) {
        this.purchaseTransactionService = purchaseTransactionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PurchaseTransaction> insert(@RequestBody PurchaseTransactionDTO dto) {
        if (isValidPurchaseTransaction(dto)) {
            return ResponseEntity.ok (purchaseTransactionService.insert(dto));
        }
        throw new ValidationException();
    }

    @GetMapping("/{id}")
    public List<ConvertedPurchaseTransactionDTO> retrieve(@RequestParam(name = "page", defaultValue = "1") int page,
                                                          @RequestParam(name = "size", defaultValue = "100") int size,
                                                          @PathVariable Long id) {
        return purchaseTransactionService.retrieve(PageRequest.of(page, size), id);
    }

    private boolean isValidPurchaseTransaction(PurchaseTransactionDTO dto) {
        int comparison = dto.getPurchaseAmount().compareTo(BigDecimal.ZERO);
        if (dto.getPurchaseAmount() == null || comparison < 0) {
            return false;
        }

        if (dto.getTransactionDate() == null) {
            return false;
        }

        if (dto.getDescription().length() > 50) {
            return false;
        }
        return true;
    }
}

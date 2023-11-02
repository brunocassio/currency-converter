package com.wex.currencyconverter.service;

import com.wex.currencyconverter.converter.ApiDataConverter;
import com.wex.currencyconverter.converter.PurchaseTransactionConverter;
import com.wex.currencyconverter.dto.*;
import com.wex.currencyconverter.exception.NoCurrencyConvertionRateAvailableException;
import com.wex.currencyconverter.exception.PurchaseTransactionNotFoundException;
import com.wex.currencyconverter.model.PurchaseTransaction;
import com.wex.currencyconverter.repository.PurchaseTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseTransactionServiceImpl implements PurchaseTransactionService{

    private PurchaseTransactionRepository purchaseTransactionRepository;
    private RatesOfExchangeService ratesOfExchangeService;

    @Autowired
    public PurchaseTransactionServiceImpl(PurchaseTransactionRepository purchaseTransactionRepository, RatesOfExchangeService ratesOfExchangeService) {
        this.purchaseTransactionRepository = purchaseTransactionRepository;
        this.ratesOfExchangeService = ratesOfExchangeService;
    }

    @Override
    public PurchaseTransaction  insert(PurchaseTransactionDTO purchaseTransactionDTO) {
        PurchaseTransaction purchaseTransaction = PurchaseTransactionConverter.convertToEntity(purchaseTransactionDTO);
        return purchaseTransactionRepository.save(purchaseTransaction);
    }

    @Override
    public List<ConvertedPurchaseTransactionDTO> retrieve(Pageable pageable, Long id) {
        PurchaseTransaction transaction = isPurchaseTransactionPresent(purchaseTransactionRepository.findById(id));

        List<ConvertedPurchaseTransactionDTO> convertedPurchaseTransactionDTOList = new ArrayList<>();

        RatesOfExchangeDTO rate = ratesOfExchangeService.fetchData(pageable.getPageNumber(), pageable.getPageSize(), transaction);
        LocalDate sixMonthsAgo = transaction.getTransactionDate().minusMonths(6);
        List<TreasuryReportingRatesDTO> reportingRates = rate.getData().stream()
                .filter(data -> data.getRecord_date().isEqual(transaction.getTransactionDate()) ||
                                (data.getRecord_date().isAfter(sixMonthsAgo)
                                        && !data.getRecord_date().isEqual(transaction.getTransactionDate())
                                        && data.getRecord_date().isBefore(transaction.getTransactionDate())
                                )
                        ).collect(Collectors.toList());

        if (reportingRates.isEmpty()) {
            throw new NoCurrencyConvertionRateAvailableException();
        }
        convertedPurchaseTransactionDTOList.add(ApiDataConverter.convertApiDataToPurchaseTransactionDTO(reportingRates, transaction));
        return convertedPurchaseTransactionDTOList;
    }

    private PurchaseTransaction isPurchaseTransactionPresent(Optional<PurchaseTransaction> transaction) {
        if (transaction.isPresent()) {
            return transaction.get();
        } else throw new PurchaseTransactionNotFoundException();
    }
}

package com.wex.currencyconverter.converter;

import com.wex.currencyconverter.dto.ConvertedAmountDTO;
import com.wex.currencyconverter.dto.ConvertedPurchaseTransactionDTO;
import com.wex.currencyconverter.dto.TreasuryReportingRatesDTO;
import com.wex.currencyconverter.model.PurchaseTransaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ApiDataConverter {

    public static ConvertedPurchaseTransactionDTO convertApiDataToPurchaseTransactionDTO(List<TreasuryReportingRatesDTO> reportingRates, PurchaseTransaction transaction) {
        com.wex.currencyconverter.dto.ConvertedPurchaseTransactionDTO convertedDTO = new ConvertedPurchaseTransactionDTO();
        convertedDTO.setId(transaction.getId());
        convertedDTO.setDescription(transaction.getDescription());
        convertedDTO.setTransactionDate(transaction.getTransactionDate());
        convertedDTO.setUsDollarAmount(transaction.getPurchaseAmount());
        convertedDTO.setConvertedAmounts(reportingRates.stream()
                .map(data -> new ConvertedAmountDTO(data.getCountry_currency_desc(),
                                                    data.getExchange_rate(),
                                                    multiplyValues(data.getExchange_rate(),
                                                    transaction.getPurchaseAmount())))
                .collect(Collectors.toList()));
        return convertedDTO;
    }

    private static BigDecimal multiplyValues(BigDecimal exchangeRate, BigDecimal purchaseAmount) {

        return BigDecimalConverter.round(BigDecimal.valueOf(exchangeRate.doubleValue() * purchaseAmount.doubleValue()));
    }
}

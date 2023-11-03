package com.wex.currencyconverter.service;

import com.wex.currencyconverter.converter.PurchaseTransactionConverter;
import com.wex.currencyconverter.dto.ConvertedPurchaseTransactionDTO;
import com.wex.currencyconverter.dto.PurchaseTransactionDTO;
import com.wex.currencyconverter.dto.RatesOfExchangeDTO;
import com.wex.currencyconverter.dto.TreasuryReportingRatesDTO;
import com.wex.currencyconverter.exception.NoCurrencyConvertionRateAvailableException;
import com.wex.currencyconverter.model.PurchaseTransaction;
import com.wex.currencyconverter.repository.PurchaseTransactionRepository;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static com.wex.currencyconverter.constants.Constants.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class PurchaseTransactionServiceTest {


    @InjectMocks
    private PurchaseTransactionServiceImpl purchaseTransactionService;
    @Mock
    private PurchaseTransactionRepository purchaseTransactionRepository;
    @Mock
    private RatesOfExchangeService ratesOfExchangeService;
    @Test
    public void testInsert() {
        PurchaseTransaction entity = getPurchaseTransaction(ID, DESCRIPTION, TRANSACTION_DATE, PURCHASE_AMOUNT);

        PurchaseTransactionDTO requestDTO = PurchaseTransactionConverter.convertToDTO(entity);
        when(purchaseTransactionRepository.save(entity)).thenReturn(entity);

        PurchaseTransaction purchaseTransaction = purchaseTransactionService.insert(requestDTO);

        Assertions.assertEquals(DESCRIPTION, purchaseTransaction.getDescription());
        Assertions.assertEquals(TRANSACTION_DATE, purchaseTransaction.getTransactionDate());
        Assertions.assertEquals(PURCHASE_AMOUNT, purchaseTransaction.getPurchaseAmount());
        Assertions.assertEquals(ID, purchaseTransaction.getId());
    }
    
    @Test
    public void testInvalidRetrieve() {
        PurchaseTransaction transaction = getPurchaseTransaction(ID, DESCRIPTION, TRANSACTION_DATE, PURCHASE_AMOUNT);
        when(purchaseTransactionRepository.findById(ID)).thenReturn(Optional.of(transaction));

        RatesOfExchangeDTO rates = new RatesOfExchangeDTO();
        rates.setData(new ArrayList<>());

        when(ratesOfExchangeService.fetchData(anyInt(), anyInt(), any())).thenReturn(rates);

        Assertions.assertThrows(NoCurrencyConvertionRateAvailableException.class, () -> {
            purchaseTransactionService.retrieve(PageRequest.of(0, 10), ID);
        });
    }

    @Test
    public void testRetrieve() {
        Pageable pageable = PageRequest.of(0, 10);
        PurchaseTransaction transaction = getPurchaseTransaction(ID, DESCRIPTION, TRANSACTION_DATE, PURCHASE_AMOUNT);
        Optional<PurchaseTransaction> optionalTransaction = Optional.of(transaction);

        RatesOfExchangeDTO rate = new RatesOfExchangeDTO();

        List<TreasuryReportingRatesDTO> reportingRates = Collections.singletonList(getTreasuryReportingRatesDTO());
        rate.setData(reportingRates);

        when(purchaseTransactionRepository.findById(anyLong())).thenReturn(optionalTransaction);
        when(ratesOfExchangeService.fetchData(anyInt(), anyInt(), any(PurchaseTransaction.class))).thenReturn(rate);

        List<ConvertedPurchaseTransactionDTO> result = purchaseTransactionService.retrieve(pageable, ID);

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(1);

        verify(purchaseTransactionRepository, times(1)).findById(anyLong());

        verify(ratesOfExchangeService, times(1)).fetchData(anyInt(), anyInt(), any(PurchaseTransaction.class));
    }

    private TreasuryReportingRatesDTO getTreasuryReportingRatesDTO() {
        return TreasuryReportingRatesDTO.builder()
                .record_date(TRANSACTION_DATE)
                .country_currency_desc(COUNTRY_CURRENCY_DESC)
                .exchange_rate(EXCHANGE_RATE)
                .build();
    }

    private PurchaseTransaction getPurchaseTransaction(long id, String description, LocalDate transactionDate, BigDecimal purchaseAmount) {
        return PurchaseTransaction.builder()
                .id(id)
                .description(description)
                .transactionDate(transactionDate)
                .purchaseAmount(purchaseAmount)
                .build();
    }
}

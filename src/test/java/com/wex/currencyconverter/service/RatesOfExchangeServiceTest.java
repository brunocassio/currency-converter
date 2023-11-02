package com.wex.currencyconverter.service;

import com.wex.currencyconverter.constants.Constants;
import com.wex.currencyconverter.dto.RatesOfExchangeDTO;
import com.wex.currencyconverter.dto.TreasuryReportingRatesDTO;
import com.wex.currencyconverter.model.PurchaseTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static com.wex.currencyconverter.constants.Constants.*;


@ExtendWith(MockitoExtension.class)
public class RatesOfExchangeServiceTest {

    @InjectMocks
    private RatesOfExchangeServiceImpl ratesOfExchangeService;
    @Mock
    private RestTemplate restTemplate;

    private static final String baseUrl = "https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange";

    @Test
    public void testFetchData() {
        RatesOfExchangeDTO expectedResponse = getRatesOfExchangeDTO();
        PurchaseTransaction purchaseTransaction = new PurchaseTransaction();
        purchaseTransaction.setTransactionDate(Constants.TRANSACTION_DATE);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("filter", "record_date:lte:" + Constants.TRANSACTION_DATE.toString());
        params.add("page[number]", "1");
        params.add("page[size]", "10");

        Mockito.when(restTemplate.getForObject(baseUrl, RatesOfExchangeDTO.class, params))
                .thenReturn(expectedResponse);

        RatesOfExchangeDTO result = ratesOfExchangeService.fetchData(1, 10, purchaseTransaction);

        Assertions.assertEquals(expectedResponse, result);

        Mockito.verify(restTemplate, Mockito.times(1))
                .getForObject(baseUrl, RatesOfExchangeDTO.class, params);
    }

    private RatesOfExchangeDTO getRatesOfExchangeDTO() {
        return RatesOfExchangeDTO.builder()
                .data(Arrays.asList(getTreasuryReportingRatesDTO()))
                .build();
    }

    private TreasuryReportingRatesDTO getTreasuryReportingRatesDTO() {
        return TreasuryReportingRatesDTO.builder()
                .record_date(TRANSACTION_DATE)
                .country_currency_desc(COUNTRY_CURRENCY_DESC)
                .exchange_rate(EXCHANGE_RATE)
                .build();
    }
}

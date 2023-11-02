package com.wex.currencyconverter.service;

import com.wex.currencyconverter.dto.RatesOfExchangeDTO;
import com.wex.currencyconverter.model.PurchaseTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class RatesOfExchangeServiceImpl implements RatesOfExchangeService{

    private static final String VALUE = "record_date:lte:";
    private RestTemplate restTemplate;
    private static final String baseUrl = "https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange";

    @Autowired
    public RatesOfExchangeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public RatesOfExchangeDTO fetchData(int page, int pageSize, PurchaseTransaction transaction) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("filter", VALUE + transaction.getTransactionDate().toString());
        params.add("page[number]", String.valueOf(page));
        params.add("page[size]", String.valueOf(pageSize));

        return restTemplate.getForObject(baseUrl, RatesOfExchangeDTO.class, params);
    }
}

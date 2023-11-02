package com.wex.currencyconverter.service;

import com.wex.currencyconverter.dto.RatesOfExchangeDTO;
import com.wex.currencyconverter.model.PurchaseTransaction;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RatesOfExchangeService {

    RatesOfExchangeDTO fetchData(int page, int pageSize, PurchaseTransaction transaction);
}

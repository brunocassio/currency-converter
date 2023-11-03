package com.wex.currencyconverter.controller;

import com.wex.currencyconverter.dto.ConvertedAmountDTO;
import com.wex.currencyconverter.dto.ConvertedPurchaseTransactionDTO;
import com.wex.currencyconverter.dto.PurchaseTransactionDTO;
import com.wex.currencyconverter.exception.ValidationException;
import com.wex.currencyconverter.model.PurchaseTransaction;
import com.wex.currencyconverter.service.PurchaseTransactionService;

import static com.wex.currencyconverter.util.Responses.EXPECTED_RETRIEVE_RESPONSE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.wex.currencyconverter.util.Responses.EXPECTED_INSERT_REPONSE;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.wex.currencyconverter.constants.Constants.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PurchaseTransactionControllerTest {

    @InjectMocks
    private PurchaseTransactionController purchaseTransactionController;
    @Mock
    private PurchaseTransactionService purchaseTransactionService;
    private MockMvc mockMvc;
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(purchaseTransactionController).build();
    }

    @Test
    public void testInsert() throws Exception {
        PurchaseTransaction entity = PurchaseTransaction.builder()
                .id(ID)
                .description(DESCRIPTION)
                .purchaseAmount(PURCHASE_AMOUNT)
                .transactionDate(TRANSACTION_DATE)
                .build();

        Mockito.when(purchaseTransactionService.insert(Mockito.any(PurchaseTransactionDTO.class))).thenReturn(entity);

        MvcResult result = mockMvc
                .perform(post("/purchase-transaction").content(EXPECTED_INSERT_REPONSE).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resp = result.getResponse().getContentAsString();
        assertEquals(EXPECTED_INSERT_REPONSE, resp);
    }

    @Test
    public void testRetrieve() throws Exception {
        List<ConvertedPurchaseTransactionDTO> dtoList = new ArrayList<>();
        ConvertedAmountDTO convertedAmountDTO = ConvertedAmountDTO.builder()
                .convertedAmount(CONVERTED_AMOUNT)
                .exchangeRate(EXCHANGE_RATE)
                .countryCurrencyDesc(COUNTRY_CURRENCY_DESC)
                .build();

        ConvertedPurchaseTransactionDTO convertedPurchaseTransactionDTO = ConvertedPurchaseTransactionDTO.builder()
                .id(ID)
                .convertedAmounts(Arrays.asList(convertedAmountDTO))
                .usDollarAmount(PURCHASE_AMOUNT)
                .transactionDate(TRANSACTION_DATE)
                .description(DESCRIPTION)
                .build();
        dtoList.add(convertedPurchaseTransactionDTO);

        Mockito.when(purchaseTransactionService.retrieve(Mockito.any(Pageable.class), Mockito.anyLong())).thenReturn(dtoList);

        MvcResult result = mockMvc.perform(get("/purchase-transaction/{id}", ID).content(EXPECTED_INSERT_REPONSE).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resp = result.getResponse().getContentAsString();
        assertEquals(EXPECTED_RETRIEVE_RESPONSE, resp);
    }

    @Test
    public void testInvalidInsert() throws Exception {
        PurchaseTransactionDTO invalidDto = new PurchaseTransactionDTO();
        invalidDto.setPurchaseAmount(null);
        invalidDto.setTransactionDate(null);
        invalidDto.setDescription("");

        Assertions.assertThrows(ValidationException.class, () -> {
            purchaseTransactionController.insert(invalidDto);
        });
    }
}

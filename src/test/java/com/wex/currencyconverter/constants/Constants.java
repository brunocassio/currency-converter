package com.wex.currencyconverter.constants;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Constants {

    public static final LocalDate TRANSACTION_DATE = LocalDate.of(2023, 10, 30);
    public static final BigDecimal PURCHASE_AMOUNT = BigDecimal.valueOf(50.00);
    public static final long ID = 0l;
    public static final String DESCRIPTION = "Test Purchase Transaction";
    public static final BigDecimal CONVERTED_AMOUNT = BigDecimal.valueOf(102.15);
    public static final BigDecimal EXCHANGE_RATE = BigDecimal.valueOf(2.043);
    public static final String COUNTRY_CURRENCY_DESC = "Brazil-Real";
}

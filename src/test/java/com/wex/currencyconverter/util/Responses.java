package com.wex.currencyconverter.util;

public abstract class Responses {

    public static final String EXPECTED_INSERT_REPONSE = "{\"id\":0,\"description\":\"Test Purchase Transaction\",\"transactionDate\":[2023,10,30],\"purchaseAmount\":50.0}";

    public static final String EXPECTED_RETRIEVE_RESPONSE = "[{\"id\":0,\"description\":\"Test Purchase Transaction\",\"transactionDate\":[2023,10,30],\"usDollarAmount\":50.0,\"convertedAmounts\":[{\"countryCurrencyDesc\":\"Brazil-Real\",\"exchangeRate\":2.043,\"convertedAmount\":102.15}]}]";

}

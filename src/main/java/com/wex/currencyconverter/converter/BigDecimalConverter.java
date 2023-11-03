package com.wex.currencyconverter.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalConverter {

    private static final int TWO_PLACES = 2;

    private BigDecimalConverter() {}

    public static BigDecimal round(BigDecimal value) {
        return value == null
                ? BigDecimal.ZERO.setScale(TWO_PLACES, RoundingMode.DOWN)
                : value.setScale(TWO_PLACES, RoundingMode.DOWN);
    }

}

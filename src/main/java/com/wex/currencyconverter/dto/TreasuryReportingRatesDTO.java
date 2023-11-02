package com.wex.currencyconverter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreasuryReportingRatesDTO implements Serializable {

    private LocalDate record_date;
    private String country;
    private String currency;
    private String country_currency_desc;
    private Double exchange_rate;
    private LocalDate effective_date;
    private Integer src_line_nbr;
    private Integer record_fiscal_year;
    private Integer record_fiscal_quarter;
    private Integer record_calendar_year;
    private Integer record_calendar_quarter;
    private Integer record_calendar_month;
    private Integer record_calendar_day;
}

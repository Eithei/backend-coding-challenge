package com.engagetech.entities.forex;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FixerIOResponse {
    Currency base;
    LocalDate date;
    Map<String, BigDecimal> rates;
}

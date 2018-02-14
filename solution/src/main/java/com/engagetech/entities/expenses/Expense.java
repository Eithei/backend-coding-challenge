package com.engagetech.entities.expenses;

import com.engagetech.entities.CrudEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiObject
public class Expense implements CrudEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiObjectField(description = "ID generated from the database.")
    private Long id;

    @JsonProperty
    @ApiObjectField(description = "Amount in GBP.")
    private BigDecimal amount;

    @JsonProperty
    @ApiObjectField(description = "VAT in GBP.")
    private BigDecimal vat;

    @JsonProperty
    @ApiObjectField(description = "Reason for the expense.")
    private String reason;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @ApiObjectField(description = "Date when the expense was inquired.")
    private LocalDate date;
}

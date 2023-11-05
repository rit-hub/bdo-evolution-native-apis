package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * Record of accrued interest
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class AccountBalance {
    @JsonProperty("AmountValue")
    private String amountValue;

    @JsonProperty("AmountCurrency")
    private CurrencyCode amountCurrency;

    @JsonProperty("DecimalPointPosition")
    private String decimalPointPosition;

}

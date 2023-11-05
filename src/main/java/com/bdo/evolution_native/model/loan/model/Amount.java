package com.bdo.evolution_native.model.loan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * Record of accrued interest
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Generated
public class Amount {
    @JsonProperty("AmountValue")
    private String amountValue;

    @JsonProperty("AmountCurrency")
    private Currencycode amountCurrency;

    @JsonProperty("DecimalPointPosition")
    private String decimalPointPosition;

}

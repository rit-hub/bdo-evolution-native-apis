package com.bdo.evolution_native.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * AmountCurrencySor
 */
@Data
@NoArgsConstructor
@Generated
public class AmountCurrencySor {
    @JsonProperty("amt")
    private BigDecimal amountValue;

    @JsonProperty("curCode")
    private String currencycode;
}

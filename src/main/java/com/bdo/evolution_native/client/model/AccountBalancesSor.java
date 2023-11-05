package com.bdo.evolution_native.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * AccountBalancesSor
 */
@Data
@NoArgsConstructor
@Generated
public class AccountBalancesSor {

    @JsonProperty("balType")
    private String amountBalanceType;

    @JsonProperty("curAmt")
    private AmountCurrencySor amountCurrency;
}

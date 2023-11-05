package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * The primary account currency
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class AccountCurrency {
    @JsonProperty("AccountCurrencyType")
    private String accountCurrencyType;

    @JsonProperty("AccountCurrency")
    private CurrencyCode currencyCode = new CurrencyCode();

}

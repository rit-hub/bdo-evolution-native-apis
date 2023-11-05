package com.bdo.evolution_native.model.addcard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * AccountCurrency
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class AccountCurrency {
    @JsonProperty("SecondaryBillingCurrency")
    private String secondaryBillingCurrency;

}

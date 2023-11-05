package com.bdo.evolution_native.model.loan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * Currencycode
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Generated
public class Currencycode {
    @JsonProperty("Currencycode")
    private String currencyCodeVal;

}

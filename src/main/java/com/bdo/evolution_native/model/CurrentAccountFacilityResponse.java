package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * Output: CR Retrieve information about a current account -
 * either standard canned reports or selected instance attribute values
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class CurrentAccountFacilityResponse {
    @JsonProperty("CurrentAccountFacility")
    private CurrentAccountFacility currentAccountFacility;

}

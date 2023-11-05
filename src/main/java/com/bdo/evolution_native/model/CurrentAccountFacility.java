package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * RetrieveCurrentAccountFacilityResponseCurrentAccountFacility
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
@JsonPropertyOrder({"ProductInstanceReference",
        "CurrentAccountNumber"})
public class CurrentAccountFacility {

    @JsonProperty("ProductInstanceReference")
    private String productInstanceReference;

    @JsonProperty(value = "CurrentAccountNumber")
    private AccountIdentification currentAccountNumber;

    @JsonUnwrapped
    private AccountFacility accountFacility;

}

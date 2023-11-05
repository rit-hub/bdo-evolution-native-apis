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
        "SavingAccountNumber"})
public class SavingAccountFacility {

    @JsonProperty("ProductInstanceReference")
    private String productInstanceReference;

    @JsonProperty(value = "SavingAccountNumber")
    private AccountIdentification savingAccountNumber;

    @JsonUnwrapped
    private AccountFacility accountFacility;

}

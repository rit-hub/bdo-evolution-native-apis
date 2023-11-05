package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * RetrieveTimeDepositFacilityResponse
 */

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
public class TimeDepositAccountFacilityResponse {
    @JsonProperty("TimeDepositFacility")
    private TimeDepositAccountFacility timeDepositFacility;

}

package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * BalanceInquiryResponse
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class CurrentBalanceInquiryResponse {
    @JsonProperty("Data")
    private CurrentAccountFacilityResponse data;

    @JsonProperty("Links")
    private Links links;

    @JsonProperty("Meta")
    private Meta meta;

}

package com.bdo.evolution_native.model.loan.model;

import com.bdo.evolution_native.model.Links;
import com.bdo.evolution_native.model.Meta;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * BalanceInquiryResponse
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Generated
public class LoanBalanceInquiryResponse {
    @JsonProperty("Data")
    private RetrieveLoanFacilityResponse data;

    @JsonProperty("Links")
    private Links links;

    @JsonProperty("Meta")
    private Meta meta;

}

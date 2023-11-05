package com.bdo.evolution_native.model.loan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * RetrieveLoanFacilityResponse
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Generated
public class RetrieveLoanFacilityResponse {
    @JsonProperty("LoanFacility")
    private RetrieveLoanFacilityResponseLoanFacility loanFacility;

}

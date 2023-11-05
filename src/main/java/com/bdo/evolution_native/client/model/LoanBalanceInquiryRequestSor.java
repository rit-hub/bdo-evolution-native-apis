package com.bdo.evolution_native.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * LoanBalanceInquiryRequest
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Generated
public class LoanBalanceInquiryRequestSor {
    @JsonProperty("loanAcctId")
    private LoanAccountId loanAccountId;

    @JsonProperty("incBal")
    private Boolean incBal;

    @JsonProperty("incExtBal")
    private Boolean incExtBal;

}

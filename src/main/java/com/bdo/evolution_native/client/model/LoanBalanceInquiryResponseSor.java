package com.bdo.evolution_native.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * LoanBalanceInquiryResponseSor
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Generated
public class LoanBalanceInquiryResponseSor {

    @JsonProperty("status")
    private StatusType status;

    @JsonProperty("loanAcctId")
    private LoanAccountId loanAccountId;

    @JsonProperty("acctBals")
    private List<AccountBalancesSor> acctBals;

}

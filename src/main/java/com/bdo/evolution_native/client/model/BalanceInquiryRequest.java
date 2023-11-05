package com.bdo.evolution_native.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * BalanceInquiryRequest
 */

/**
 * BalanceInquiryRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class BalanceInquiryRequest {
    @JsonProperty("depAcctId")
    private DepartmentAccountId departmentAccountId;

    @JsonProperty("incBal")
    private Boolean incBal;

    @JsonProperty("incExtBal")
    private Boolean incExtBal;

}

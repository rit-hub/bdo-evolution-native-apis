package com.bdo.evolution_native.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * BalanceInquiryResponse
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class BalanceInquiryResponse {

    @JsonProperty("status")
    private StatusType status;

    @JsonProperty("depAcctId")
    private DepartmentAccountId depAcctId;

    @JsonProperty("acctBals")
    private List<AccountBalancesSor> acctBals;

}

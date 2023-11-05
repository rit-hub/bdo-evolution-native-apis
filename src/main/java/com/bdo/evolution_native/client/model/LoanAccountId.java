package com.bdo.evolution_native.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * LoanAccountId
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Generated
public class LoanAccountId {
    @JsonProperty("acctId")
    private String acctId;

    @JsonProperty("acctType")
    private String acctType;

}

package com.bdo.evolution_native.client.model.customerlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * The type Bank acct id type.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class BankAcctIdType {
    @JsonProperty("acctType")
    private String acctType;

    @JsonProperty("acctId")
    private String acctId;

    @JsonProperty("acctIdMasked")
    private String acctIdMasked;
}

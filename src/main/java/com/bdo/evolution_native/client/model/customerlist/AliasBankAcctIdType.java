package com.bdo.evolution_native.client.model.customerlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * The type Alias bank acct id type.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class AliasBankAcctIdType {
    @JsonProperty("aliasAcctType")
    private String aliasAcctType;

    @JsonProperty("aliasAcctId")
    private String aliasAcctId;

    @JsonProperty("aliasAcctIdMasked")
    private String aliasAcctIdMasked;

}

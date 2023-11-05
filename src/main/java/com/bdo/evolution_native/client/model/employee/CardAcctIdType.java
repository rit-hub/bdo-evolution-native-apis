package com.bdo.evolution_native.client.model.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CardAcctIdType
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CardAcctIdType {
    @JsonProperty("acctId")
    private String acctId;

    @JsonProperty("acctIdMasked")
    private String acctIdMasked;

    @JsonProperty("acctType")
    private String acctType;

}

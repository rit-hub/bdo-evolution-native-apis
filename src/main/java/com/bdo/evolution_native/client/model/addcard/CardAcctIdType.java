package com.bdo.evolution_native.client.model.addcard;

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
    private String acctId = null;

    @JsonProperty("acctIdMasked")
    private String acctIdMasked = null;

    @JsonProperty("acctType")
    private String acctType = null;

}

package com.bdo.evolution_native.client.model.addcard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * AcctLinkType
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class AcctLinkType {
    @JsonProperty("bankAcctId")
    private BankAcctIdType bankAcctId = null;

    @JsonProperty("seqId")
    private Integer seqId = null;

}

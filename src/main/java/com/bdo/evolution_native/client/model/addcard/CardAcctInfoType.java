package com.bdo.evolution_native.client.model.addcard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CardAcctInfoType
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CardAcctInfoType {
    @JsonProperty("cardAcctId")
    private CardAcctIdType cardAcctId = null;

    @JsonProperty("bankId")
    private String bankId = null;

    @JsonProperty("isoId")
    private String isoId = null;

    @JsonProperty("memberId")
    private String memberId = null;

    @JsonProperty("cardTypeCode")
    private String cardTypeCode = null;

}

package com.bdo.evolution_native.client.model.addcard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CustIdType
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustIdType {
    @JsonProperty("custPermId")
    private String custPermId = null;

    @JsonProperty("custPermIdMasked")
    private String custPermIdMasked = null;

}

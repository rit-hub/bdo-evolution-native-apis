package com.bdo.evolution_native.client.model.customerlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * The type Cust id type.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustIdType {
    @JsonProperty("custPermId")
    private String custPermId;

    @JsonProperty("custPermIdMasked")
    private String custPermIdMasked;

}

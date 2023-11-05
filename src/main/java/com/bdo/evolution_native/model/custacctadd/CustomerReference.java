package com.bdo.evolution_native.model.custacctadd;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CustomerReference
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class CustomerReference {
    @JsonProperty("RequestId")
    private String requestId;

}

package com.bdo.evolution_native.client.model.customerlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * The type Ssnid type.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class SSNIDType {
    @JsonProperty("id")
    private String id;

    @JsonProperty("idMasked")
    private String idMasked;

}

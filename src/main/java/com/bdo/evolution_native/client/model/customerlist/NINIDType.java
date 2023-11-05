package com.bdo.evolution_native.client.model.customerlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * The type Ninid type.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class NINIDType {
    @JsonProperty("id")
    private String id;

    @JsonProperty("idMasked")
    private String idMasked;

}

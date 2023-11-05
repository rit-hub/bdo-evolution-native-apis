package com.bdo.evolution_native.client.model.customerlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * The type Rec ctrl in type.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class RecCtrlInType {
    @JsonProperty("maxRec")
    private Integer maxRec;

    @JsonProperty("cursor")
    private String cursor;

}

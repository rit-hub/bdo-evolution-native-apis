package com.bdo.evolution_native.client.model.customerlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * The type Rec ctrl out type.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class RecCtrlOutType {
    @JsonProperty("matchedRec")
    private Integer matchedRec;

    @JsonProperty("sentRec")
    private Integer sentRec;

    @JsonProperty("cursor")
    private String cursor;

}

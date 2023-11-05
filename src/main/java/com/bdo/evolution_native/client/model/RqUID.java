package com.bdo.evolution_native.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * The type Rq uid.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class RqUID {
    @JsonProperty("rqUID")
    private String requestUid;

}

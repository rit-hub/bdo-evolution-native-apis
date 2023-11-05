package com.bdo.evolution_native.client.model.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * RqUID
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class RqUID {
    @JsonProperty("rqUID")
    private String rqUIDField;

}

package com.bdo.evolution_native.model.retrivemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CustomerId
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustomerId {
    @JsonProperty("CustomerNumber")
    private String customerNumber;

}

package com.bdo.evolution_native.model.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Links relevant to the payload
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class Links {
    @JsonProperty("Self")
    private String self;

    @JsonProperty("First")
    private String first;

    @JsonProperty("Prev")
    private String prev;

    @JsonProperty("Next")
    private String next;

    @JsonProperty("Last")
    private String last;

}

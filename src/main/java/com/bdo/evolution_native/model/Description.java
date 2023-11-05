package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * Description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Description {
    @JsonProperty("Text")
    private String textValue;

}

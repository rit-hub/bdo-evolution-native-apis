package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * FieldName by which a party is known and which is usually used to identify that party. (ISO20022)
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class FieldName {
    @JsonProperty("Name")
    private String nameValue;

}

package com.bdo.evolution_native.model.loan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
/**
 * The various key dates and times associated with the payment transaction
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Generated
public class Date {
    @JsonProperty("DateContent")
    private String dateContent;

}

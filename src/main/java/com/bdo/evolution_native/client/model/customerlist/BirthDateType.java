package com.bdo.evolution_native.client.model.customerlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * The type Birth date type.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class BirthDateType {
    @JsonProperty("date")
    private String date;

    @JsonProperty("dateMasked")
    private String dateMasked;

}

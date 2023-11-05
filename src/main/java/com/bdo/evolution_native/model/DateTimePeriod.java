package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * Datetimeperiod
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class DateTimePeriod {
    @JsonProperty("FromDateTime")
    private DateTime fromDateTime = new DateTime();

    @JsonProperty("ToDateTime")
    private DateTime toDateTime = new DateTime();

}

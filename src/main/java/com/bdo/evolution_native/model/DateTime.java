package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * Datetime
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class DateTime {
    @JsonProperty("DateTimeContent")
    private String dateTimeContent;

    @JsonProperty("TimeZoneCode")
    private String timeZoneCode;

    @JsonProperty("DaylightSavingIndicator")
    private String daylightSavingIndicator;

}

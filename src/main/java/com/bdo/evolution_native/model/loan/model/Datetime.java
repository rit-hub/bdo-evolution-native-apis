package com.bdo.evolution_native.model.loan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * Datetime
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Generated
public class Datetime {
    @JsonProperty("DateTimeContent")
    private String dateTimeContent;

    @JsonProperty("TimeZoneCode")
    private String timeZoneCode;

    @JsonProperty("DaylightSavingIndicator")
    private String daylightSavingIndicator;

}

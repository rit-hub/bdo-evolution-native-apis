package com.bdo.evolution_native.model.loan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * The processing schedule for applying interest rates to the account
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Generated
public class Rate {
    @JsonProperty("RateValue")
    private String rateValue;

    @JsonProperty("RateUnit")
    private String rateUnit;

    @JsonProperty("RatePeriod")
    private String ratePeriod;

    @JsonProperty("RateCapitalUnit")
    private String rateCapitalUnit;

}

package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * Status
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Status {
    @JsonProperty("StatusReason")
    private Description statusReason = new Description();

    @JsonProperty("StatusDateTime")
    private DateTime statusDateTime = new DateTime();

    @JsonProperty("StatusValidityPeriod")
    private DateTimePeriod statusValidityPeriod = new DateTimePeriod();

    @JsonProperty("StatusInvolvedParty")
    private InvolvedParty statusInvolvedParty = new InvolvedParty();

}

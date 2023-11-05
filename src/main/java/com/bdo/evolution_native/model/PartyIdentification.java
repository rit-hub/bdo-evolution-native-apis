package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * Partyidentification
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class PartyIdentification {
    @JsonProperty("PartyIdentificationType")
    private String partyIdentificationType;

    @JsonProperty("PartyIdentification")
    private String partyIdentificationName;

}

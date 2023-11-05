package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * Identifier
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Identifier {
    @JsonProperty("IdentifierValue")
    private FieldValue identifierFieldValue = new FieldValue();

    @JsonProperty("IdentifierIssuingAuthority")
    private InvolvedParty identifierIssuingAuthority = new InvolvedParty();

    @JsonProperty("IdentifierStartDate")
    private DateTime identifierStartDate = new DateTime();

    @JsonProperty("IdentifierEndDate")
    private DateTime identifierEndDate = new DateTime();

}

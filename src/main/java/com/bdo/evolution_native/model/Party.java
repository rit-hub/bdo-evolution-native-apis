package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * Party
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Party {
    @JsonProperty("PartyName")
    private FieldName partyFieldName = new FieldName();

    @JsonProperty("PartyType")
    private String partyType;

    @JsonProperty("PartyDateTime")
    private DateTime partyDateTime = new DateTime();

    @JsonProperty("PartyIdentification")
    private PartyIdentification partyIdentification = new PartyIdentification();

}

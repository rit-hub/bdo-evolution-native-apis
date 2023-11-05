package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * Involvedparty
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class InvolvedParty {
    @JsonProperty("PartyReference")
    private Party partyReference = new Party();

    @JsonProperty("PartyInvolvement")
    private PartyRole partyInvolvement = new PartyRole();

}

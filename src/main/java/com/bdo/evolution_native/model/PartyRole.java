package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * Partyrole
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class PartyRole {
    @JsonProperty("PartyRoleType")
    private Description partyRoleType = new Description();

    @JsonProperty("PartyRoleName")
    private FieldName partyRoleFieldName = new FieldName();

    @JsonProperty("PartyRoleValidityPeriod")
    private DateTimePeriod partyRoleValidityPeriod = new DateTimePeriod();

    @JsonProperty("PartyInvolvementType")
    private String partyInvolvementType;

}

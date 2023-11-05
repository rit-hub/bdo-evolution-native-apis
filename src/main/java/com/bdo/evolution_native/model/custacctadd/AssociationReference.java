package com.bdo.evolution_native.model.custacctadd;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * AssociationReference
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class AssociationReference {
    @JsonProperty("Associations")
    private String associations;

    @JsonProperty("AssociationType")
    private String associationType;

    @JsonProperty("AssociationObligationOrEntitlement")
    private String associationObligationOrEntitlement;

}

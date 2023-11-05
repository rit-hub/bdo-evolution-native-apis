package com.bdo.evolution_native.client.model.custacctadd;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

/**
 * AcctRelationTypeTaxLiability
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class AcctRelationTypeTaxLiability {
    @JsonProperty("relationCode")
    private String relationCode = null;

    @JsonProperty("custId")
    private CustIdType custId = null;

    @JsonProperty("taxLiabilityPctAmt")
    private BigDecimal taxLiabilityPctAmt = null;

    @JsonProperty("noticeFlagDescription")
    private String noticeFlagDescription = null;
}

package com.bdo.evolution_native.model.retrivemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * TaxDetails
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaxDetails {
    @JsonProperty("TaxId")
    private String taxId;
    @JsonProperty("VatExemptionCode")
    private String vatExemptionCode;

    @JsonProperty("AffectedTaxYearCode")
    private String affectedTaxYearCode;

}

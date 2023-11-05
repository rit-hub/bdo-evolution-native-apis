package com.bdo.evolution_native.model.initiatemodel;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * TaxDetails
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@Valid
public class TaxDetails {
  @JsonProperty("TaxId")
  @Size(max = 10, message = EvolutionConstantUtils.TAXID_INITIATE_SIZE)
  private String taxId;

  @JsonProperty("VatExemptionCode")
  @Size(max = 3, message = EvolutionConstantUtils.VATEXEMPTIONCODE_SIZE_VALIDATION)
  private String vatExemptionCode;

  @JsonProperty("AffectedTaxYearCode")
  private String affectedTaxYearCode;
}
package com.bdo.evolution_native.model.initiatemodel;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * NationalDetails
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@Valid
public class NationalDetails {
  @JsonProperty("NationalId")
  @Size(max = 10, message = EvolutionConstantUtils.NATIONAL_ID_SIZE_MESSAGE)
  private String nationalId;
}
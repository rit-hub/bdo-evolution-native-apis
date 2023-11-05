package com.bdo.evolution_native.model.initiatemodel;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * MotoAccountType
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@Valid
public class MotoAccountType {
  @JsonProperty("ExpiryDate")
  @Pattern(regexp = EvolutionConstantUtils.REGEX_FOR_DATE, message = EvolutionConstantUtils.DATE_FORMAT_MESSAGE)
  private String expiryDate;
}
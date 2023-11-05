package com.bdo.evolution_native.model.initiatemodel;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.bdo.evolution_native.enums.PhoneType;
import com.bdo.evolution_native.util.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * PhoneNumber
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@Valid
public class PhoneNumber {
  @JsonProperty("PhoneType")
  @Size(max = 20, message = EvolutionConstantUtils.PHONE_TYPE_SIZE_MESSAGE)
  @ValueOfEnum(enumClass = PhoneType.class, message = EvolutionConstantUtils.PHONE_TYPE_FORMAT_MESSAGE)
  private String phoneType;

  @JsonProperty("Phone")
  private String phone;

  @JsonProperty("InitialDialCode")
  private String initialDialCode;

  @JsonProperty("Extension")
  private String extension;
}
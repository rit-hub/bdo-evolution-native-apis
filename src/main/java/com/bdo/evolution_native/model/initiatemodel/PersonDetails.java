package com.bdo.evolution_native.model.initiatemodel;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * PersonDetails
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@Valid
public class PersonDetails {
  @JsonProperty("LastName")
  @Size(max = 20, message = EvolutionConstantUtils.LAST_NAME_SIZE_MESSAGE)
  private String lastName;

  @JsonProperty("FirstName")
  @Size(max = 20, message = EvolutionConstantUtils.FIRST_NAME_SIZE_MESSAGE)
  private String firstName;

  @JsonProperty("MiddleName")
  @Size(max = 20, message = EvolutionConstantUtils.MIDDLE_NAME_SIZE_MESSAGE)
  private String middleName;

  @JsonProperty("TitlePrefix")
  private String titlePrefix;

  @JsonProperty("NameSuffix")
  @Size(max = 6, message = EvolutionConstantUtils.NAME_SUFFIX_SIZE_MESSAGE)
  private String nameSuffix;

  @JsonProperty("BusinessName")
  @Size(max = 40, message = EvolutionConstantUtils.BUSINESS_NAME_SIZE_MESSAGE)
  private String businessName;
}
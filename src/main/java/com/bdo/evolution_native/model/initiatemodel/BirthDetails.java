package com.bdo.evolution_native.model.initiatemodel;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * BirthDetails
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@Valid
public class BirthDetails {
  @JsonProperty("BirthDate")
  @Pattern(regexp = EvolutionConstantUtils.REGEX_FOR_DATE, message = EvolutionConstantUtils.BIRTHDATE_VALIDATION)
  private String birthDate;

  @JsonProperty("CityOfBirth")
  private String cityOfBirth;

  @JsonProperty("BirthProvince")
  private String birthProvince;

  @JsonProperty("CountryOfBirth")
  private String countryOfBirth;

}

package com.bdo.evolution_native.client.model.initiatemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * PersonFullNameType
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class PersonFullNameType {
  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("secondSurname")
  private String secondSurname;

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("middleName")
  private String middleName;

  @JsonProperty("titlePrefix")
  private String titlePrefix;

  @JsonProperty("nameSuffix")
  private String nameSuffix;

  @JsonProperty("businessName")
  private String businessName;
}
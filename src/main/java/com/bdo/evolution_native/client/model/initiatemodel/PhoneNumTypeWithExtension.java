package com.bdo.evolution_native.client.model.initiatemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * PhoneNumTypeWithExtension
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class PhoneNumTypeWithExtension {
  @JsonProperty("phoneType")
  private String phoneType;

  @JsonProperty("phone")
  private String phone;

  @JsonProperty("intlDialCode")
  private String intlDialCode;

  @JsonProperty("phoneExt")
  private String phoneExt;
}
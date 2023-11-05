package com.bdo.evolution_native.model.initiatemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;

/**
 * AddCustomerRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class AddCustomerRequest {
  @JsonProperty("CustomerProfileDetails")
  private @Valid CustomerProfileDetails customerProfileDetails;

}

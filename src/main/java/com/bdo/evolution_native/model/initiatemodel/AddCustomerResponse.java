package com.bdo.evolution_native.model.initiatemodel;

import com.bdo.evolution_native.model.Links;
import com.bdo.evolution_native.model.Meta;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * AddCustomerResponse
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class AddCustomerResponse {
  @JsonProperty("Data")
  private AddCustomerResponseData data;

  @JsonProperty("Links")
  private Links links;

  @JsonProperty("Meta")
  private Meta meta;

}

package com.bdo.evolution_native.model.initiatemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * AddCustomerResponseDataCustomerId
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class AddCustomerResponseDataCustomerId {
  @JsonProperty("CustomerNumber")
  private String customerNumber;

}

package com.bdo.evolution_native.model.initiatemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
/**
 * AddCustomerResponseData
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class AddCustomerResponseData {
  @JsonProperty("RequestId")
  private String requestId;

  @JsonProperty("CardAccountDetails")
  private AddCustomerResponseDataCardAccountDetails cardAccountDetails;

  @JsonProperty("CustomerId")
  private AddCustomerResponseDataCustomerId customerId;

}

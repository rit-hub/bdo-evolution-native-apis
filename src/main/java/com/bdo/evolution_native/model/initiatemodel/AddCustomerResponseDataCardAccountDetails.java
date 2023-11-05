package com.bdo.evolution_native.model.initiatemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * AddCustomerResponseDataCardAccountDetails
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class AddCustomerResponseDataCardAccountDetails {
  @JsonProperty("AccountId")
  private String accountId;

  @JsonProperty("AccountType")
  private String accountType;

}

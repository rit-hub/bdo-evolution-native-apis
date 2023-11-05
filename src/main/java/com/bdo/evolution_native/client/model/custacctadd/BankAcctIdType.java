package com.bdo.evolution_native.client.model.custacctadd;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
/**
 * BankAcctIdType
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class BankAcctIdType {
  @JsonProperty("acctType")
  private String acctType = null;

  @JsonProperty("acctId")
  private String acctId = null;

  @JsonProperty("acctIdMasked")
  private String acctIdMasked = null;
}

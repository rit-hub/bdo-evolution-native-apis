package com.bdo.evolution_native.client.model.initiatemodel;

import com.bdo.evolution_native.client.model.StatusType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CustProfBasicAddRs
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustProfBasicAddRs {
  @JsonProperty("rqUID")
  private RqUID rqUID;

  @JsonProperty("status")
  private StatusType status;

  @JsonProperty("custId")
  private CustIdType custId;

  @JsonProperty("cardAcctId")
  private CardAcctIdType cardAcctId;
}
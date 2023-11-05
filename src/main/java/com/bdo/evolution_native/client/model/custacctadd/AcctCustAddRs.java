package com.bdo.evolution_native.client.model.custacctadd;

import com.bdo.evolution_native.client.model.RqUID;
import com.bdo.evolution_native.client.model.StatusType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
/**
 * AcctCustAddRs
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class AcctCustAddRs {
  @JsonProperty("rqUID")
  private RqUID rqUID = null;

  @JsonProperty("status")
  private StatusType status = null;
}

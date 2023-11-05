package com.bdo.evolution_native.client.model.initiatemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CustProfBasicAddRq
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustProfBasicAddRq {
  @JsonProperty("rqUID")
  private RqUID rqUID;

  @JsonProperty("custProfBasic")
  private CustProfBasicType custProfBasic;
}
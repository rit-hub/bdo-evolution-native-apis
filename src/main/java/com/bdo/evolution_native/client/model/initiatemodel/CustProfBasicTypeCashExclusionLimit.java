package com.bdo.evolution_native.client.model.initiatemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
/**
 * CustProfBasicTypeCashExclusionLimit
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustProfBasicTypeCashExclusionLimit {
  @JsonProperty("amt")
  private BigDecimal amt;
}
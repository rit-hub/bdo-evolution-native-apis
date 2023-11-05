package com.bdo.evolution_native.model.initiatemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
/**
 * CashExclusionLimit
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CashExclusionLimit {
  @JsonProperty("Amount")
  private BigDecimal amount;

}

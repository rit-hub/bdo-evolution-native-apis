package com.bdo.evolution_native.client.model.initiatemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
/**
 * CustProfBasicTypeEmpIncomeAmt
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustProfBasicTypeEmpIncomeAmt {
  @JsonProperty("amt")
  private BigDecimal amt;
}
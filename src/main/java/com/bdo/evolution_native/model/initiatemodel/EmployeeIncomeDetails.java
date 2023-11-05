package com.bdo.evolution_native.model.initiatemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
/**
 * EmployeeIncomeDetails
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class EmployeeIncomeDetails {
  @JsonProperty("EmploymentIncomeSource")
  private String employmentIncomeSource;

  @JsonProperty("EmployeeIncomeAmount")
  private BigDecimal employeeIncomeAmount;
}
package com.bdo.evolution_native.model.employee;

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
    @JsonProperty("EmployeeIncomeAmount")
    private BigDecimal employeeIncomeAmount;

}

package com.bdo.evolution_native.model.retrivemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeIncomeDetails {
    @JsonProperty("EmploymentIncomeSource")
    private String employmentIncomeSource;

    @JsonProperty("EmployeeIncomeAmount")
    private BigDecimal employeeIncomeAmount;

}

package com.bdo.evolution_native.model.retrivemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * EmploymentDetails
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmploymentDetails {
    @JsonProperty("EmployeeIncomeDetails")
    private EmployeeIncomeDetails employeeIncomeDetails;

    @JsonProperty("YearsAtJob")
    private Integer yearsAtJob;

    @JsonProperty("ProfessionCode")
    private String professionCode;

    @JsonProperty("EmploymentStatus")
    private String employmentStatus;

    @JsonProperty("BusinessEmailAddress")
    private String businessEmailAddress;

    @JsonProperty("EmployeeCode")
    private String employeeCode;

    @JsonProperty("BDOEmployeeNumber")
    private String bdOEmployeeNumber;

    @JsonProperty("NatureOfWorkEmployed")
    private String natureOfWorkEmployed;

    @JsonProperty("NatureOfWorkSelf")
    private String natureOfWorkSelf;

}

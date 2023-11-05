package com.bdo.evolution_native.model.initiatemodel;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * EmploymentDetails
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@Valid
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
  @Size(max = 80, message = EvolutionConstantUtils.BUISNESS_EMAIL_SIZE_MESSAGE)
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
      , message = EvolutionConstantUtils.EMAIL_VALIDATION)
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
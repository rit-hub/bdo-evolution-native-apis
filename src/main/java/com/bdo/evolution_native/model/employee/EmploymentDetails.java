package com.bdo.evolution_native.model.employee;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
public class EmploymentDetails {
    @JsonProperty("EmployeeAddressDetail")
    private EmployeeAddressDetail employeeAddressDetail;

    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|1\\d|2\\d|30|31)$"
        , message = EvolutionConstantUtils.DATE_VALIDATION)
    @JsonProperty("EmploymentStartDate")
    private String employmentStartDate;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        , message = EvolutionConstantUtils.EMAIL_VALIDATION)
    @JsonProperty("BusinessEmailAddress")
    private String businessEmailAddress;

    @JsonProperty("EmployeeIncomeDetails")
    private EmployeeIncomeDetails employeeIncomeDetails;

    @JsonProperty("EmployerId")
    private String employerId;

    @JsonProperty("EmploymentPhoneAvailableCode")
    private String employmentPhoneAvailableCode;

    @JsonProperty("FaxNumber")
    private String faxNumber;

    @JsonProperty("PhoneNumber")
    private String phoneNumber;

    @JsonProperty("PhoneNumberExtension")
    private String phoneNumberExtension;

    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|1\\d|2\\d|30|31)$"
        , message = EvolutionConstantUtils.DATE_VALIDATION)
    @JsonProperty("EmploymentStopDate")
    private String employmentStopDate;

    @JsonProperty("EmploymentType")
    private String employmentType;
    @Size(max = 30, message = EvolutionConstantUtils.JOB_TITLE_TYPE_SIZE_VALIDATION)
    @JsonProperty("JobTitleType")
    private String jobTitleType;

    @JsonProperty("EmployerSicCode")
    private String employerSicCode;
    @Size(max = 20, message = EvolutionConstantUtils.PAYROLLID_SIZE_VALIDATION)
    @JsonProperty("PayrollId")
    private String payrollId;

}

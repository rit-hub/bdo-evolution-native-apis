package com.bdo.evolution_native.client.model.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CustEmpRecType3
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustEmpRecType {
    @JsonProperty("empAddr")
    private EmpAddrType empAddr;

    @JsonProperty("empStartDt")
    private String empStartDt;

    @JsonProperty("busEmailAddr")
    private String busEmailAddr;

    @JsonProperty("empIncomeAmt")
    private CustProfBasicTypeEmpIncomeAmt empIncomeAmt;

    @JsonProperty("employerId")
    private String employerId;

    @JsonProperty("empPhoneAvailCode")
    private String empPhoneAvailCode;

    @JsonProperty("faxNumber")
    private String faxNumber;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("phoneNumberExt")
    private String phoneNumberExt;

    @JsonProperty("empStopDt")
    private String empStopDt;

    @JsonProperty("empType")
    private String empType;

    @JsonProperty("jobTitleType")
    private String jobTitleType;

    @JsonProperty("sicCode")
    private String sicCode;

    @JsonProperty("payrollId")
    private String payrollId;

}

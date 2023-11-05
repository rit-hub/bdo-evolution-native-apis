package com.bdo.evolution_native.client.model.customerlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * The type Cust basic dtl type.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustBasicDtlType {
    @JsonProperty("custId")
    private CustIdType custId;

    @JsonProperty("custType")
    private String custType;

    @JsonProperty("custMailCustCode")
    private String custMailCustCode;

    @JsonProperty("taxId")
    private SSNIDType taxId;

    @JsonProperty("nationalId")
    private NINIDType nationalId;

    @JsonProperty("phoneNum")
    private PhoneNumTypeWithExtension phoneNum;

    @JsonProperty("birthDt")
    private BirthDateType birthDt;

    @JsonProperty("custAddr")
    private NonParsedAddrType custAddr;

    @JsonProperty("shortName")
    private String shortName;

    @JsonProperty("custStatusCode")
    private String custStatusCode;

    @JsonProperty("taxInfoCode")
    private TINIDType taxInfoCode;

    @JsonProperty("accessId")
    private String accessId;

    @JsonProperty("custDlrDlrGroupFlag")
    private String custDlrDlrGroupFlag;

    @JsonProperty("bankId")
    private String bankId;

    @JsonProperty("branchId")
    private String branchId;

    @JsonProperty("emailAddress")
    private String emailAddress;

    @JsonProperty("mobileNoUpdDt")
    private String mobileNoUpdDt;

    @JsonProperty("custAcctRelType")
    private String custAcctRelType;

    @JsonProperty("emailAddUpdDt")
    private String emailAddUpdDt;

    @JsonProperty("citizenCode")
    private String citizenCode;

    @JsonProperty("residenceCode")
    private String residenceCode;

    @JsonProperty("riskCode")
    private String riskCode;

}

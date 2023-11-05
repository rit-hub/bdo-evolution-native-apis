package com.bdo.evolution_native.client.model.customerlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * The type Cust list inq rq.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustListInqRq {
    @JsonProperty("rqUID")
    private RqUID rqUID;

    @JsonProperty("recCtrlIn")
    private RecCtrlInType recCtrlIn;

    @JsonProperty("customerType")
    private String customerType;

    @JsonProperty("searchType")
    private String searchType;

    @JsonProperty("searchValue")
    private String searchValue;

    @JsonProperty("birthDt")
    private String birthDt;

    @JsonProperty("aliasBankAcctId")
    private AliasBankAcctIdType aliasBankAcctId;

    @JsonProperty("postalCode")
    private String postalCode;

    @JsonProperty("accessCode")
    private String accessCode;

    @JsonProperty("debitCardNumber")
    private String debitCardNumber;

    @JsonProperty("taxId")
    private String taxId;

    @JsonProperty("shortName")
    private String shortName;

    @JsonProperty("bankAcctId")
    private BankAcctIdType bankAcctId;

    @JsonProperty("nationalId")
    private String nationalId;

    @JsonProperty("phoneNum")
    private PhoneNumTypeWithExtension phoneNum;

    @JsonProperty("userKey")
    private String userKey;

    @JsonProperty("taxInfoCode")
    private String taxInfoCode;

    @JsonProperty("custId")
    private String custId;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("secondName")
    private String secondName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("businessName")
    private String businessName;

    @JsonProperty("mobileNum")
    private String mobileNum;

    @JsonProperty("eMailAddress")
    private String eMailAddress;

    @JsonProperty("compliment")
    private String compliment;

    @JsonProperty("incDealerCode")
    private String incDealerCode;

    @JsonProperty("searchOption")
    private String searchOption;

}

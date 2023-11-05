package com.bdo.evolution_native.model.retrivemodel;

import com.bdo.evolution_native.model.initiatemodel.Others;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * CustomerProfileDetails
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerProfileDetails {
    @JsonProperty("RequestId")
    private String requestId;

    @JsonProperty("CardAccountDetails")
    private CardAccountDetails cardAccountDetails;

    @JsonProperty("MotoAccountType")
    private MotoAccountType motoAccountType;

    @JsonProperty("CustomerProfileBasic")
    private CustomerProfileBasic customerProfileBasic;

    @JsonProperty("EmploymentDetails")
    private EmploymentDetails employmentDetails;

    @JsonProperty("PersonDetails")
    private PersonDetails personDetails;

    @JsonProperty("CustomerAddressDetail")
    private CustomerAddressDetail customerAddressDetail;

    @JsonProperty("ParsedFlag")
    private String parsedFlag;

    @JsonProperty("SanctionFlag")
    private String sanctionFlag;

    @JsonProperty("PhoneNumberDetails")
    private List<PhoneNumber> phoneNumberDetails;

    @JsonProperty("EmailAddress")
    private String emailAddress;

    @JsonProperty("GenderCode")
    private String genderCode;

    @JsonProperty("RaceCode")
    private String raceCode;

    @JsonProperty("TaxDetails")
    private TaxDetails taxDetails;

    @JsonProperty("SicCode")
    private String sicCode;

    @JsonProperty("CustomerDetails")
    private CustomerDetails customerDetails;

    @JsonProperty("CashExclusionCode")
    private String cashExclusionCode;

    @JsonProperty("CashExclusionLimit")
    private CashExclusionLimit cashExclusionLimit;

    @JsonProperty("CustomerExtractCode")
    private String customerExtractCode;

    @JsonProperty("TaxIdCertificateCode")
    private String taxIdCertificateCode;

    @JsonProperty("TinActivityDate")
    private String tinActivityDate;

    @JsonProperty("TaxIdTypeCode")
    private String taxIdTypeCode;

    @JsonProperty("NumberOfW8OrW9")
    private Integer numberOfW8OrW9;

    @JsonProperty("UserDefinedFlag3")
    private String userDefinedFlag3;

    @JsonProperty("AliasDetails")
    private AliasDetails aliasDetails;

    @JsonProperty("MemoFlag")
    private String memoFlag;

    @JsonProperty("RequestMemoFlag")
    private String requestMemoFlag;

    @JsonProperty("TicklerFlag")
    private String ticklerFlag;

    @JsonProperty("RequestTicklerFlag")
    private String requestTicklerFlag;

    @JsonProperty("MoveInConferenceCode")
    private String moveInConferenceCode;

    @JsonProperty("EveningPhoneAvailabilityCode")
    private String eveningPhoneAvailabilityCode;

    @JsonProperty("DayPhoneAvailabilityCode")
    private String dayPhoneAvailabilityCode;

    @JsonProperty("TicklerNumber")
    private String ticklerNumber;

    @JsonProperty("TelexAnswerBackNumber")
    private String telexAnswerBackNumber;

    @JsonProperty("ActivityLevelCode")
    private String activityLevelCode;

    @JsonProperty("SourceCode")
    private String sourceCode;

    @JsonProperty("LifeStageCode")
    private String lifeStageCode;

    @JsonProperty("BehavioralCode")
    private String behavioralCode;

    @JsonProperty("ResponsivenessCode")
    private String responsivenessCode;

    @JsonProperty("ContactPreferenceText")
    private String contactPreferenceText;

    @JsonProperty("UserKey")
    private String userKey;

    @JsonProperty("CustomerLinkage")
    private String customerLinkage;

    @JsonProperty("LoyaltyCardNumber")
    private String loyaltyCardNumber;

    @JsonProperty("AccessCode")
    private String accessCode;

    @JsonProperty("Others")
    private Others others;

}

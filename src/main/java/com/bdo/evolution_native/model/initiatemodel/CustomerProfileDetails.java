package com.bdo.evolution_native.model.initiatemodel;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
/**
 * CustomerProfileDetails
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@Valid
public class CustomerProfileDetails {
  @JsonProperty("RequestId")
  private String requestId;

  @JsonProperty("CardAccountDetails")
  private @Valid CardAccountDetails cardAccountDetails;

  @JsonProperty("MotoAccountType")
  private @Valid MotoAccountType motoAccountType;

  @JsonProperty("CustomerProfileBasic")
  private @Valid CustomerProfileBasic customerProfileBasic;

  @JsonProperty("EmploymentDetails")
  private @Valid EmploymentDetails employmentDetails;

  @JsonProperty("PersonDetails")
  private @Valid PersonDetails personDetails;

  @JsonProperty("CustomerAddressDetail")
  private @Valid CustomerAddressDetail customerAddressDetail;

  @JsonProperty("ParsedFlag")
  @Size(max = 1, message = EvolutionConstantUtils.PARSED_FLAG_SIZE_MESSAGE)
  private String parsedFlag;

  @JsonProperty("SanctionFlag")
  private String sanctionFlag;

  @JsonProperty("PhoneNumberDetails")
  private @Valid List<PhoneNumber> phoneNumberDetails;

  @JsonProperty("EmailAddress")
  @Size(max = 80, message = EvolutionConstantUtils.EMAIL_SIZE_VALIDATION)
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
      , message = EvolutionConstantUtils.BUISNESS_EMAIL_SIZE_MESSAGE)
  private String emailAddress;

  @JsonProperty("GenderCode")
  private String genderCode;

  @JsonProperty("RaceCode")
  private String raceCode;

  @JsonProperty("TaxDetails")
  private @Valid TaxDetails taxDetails;

  @JsonProperty("SicCode")
  private String sicCode;

  @JsonProperty("CustomerDetails")
  private @Valid CustomerDetails customerDetails;

  @JsonProperty("CashExclusionCode")
  private String cashExclusionCode;

  @JsonProperty("CashExclusionLimit")
  private CashExclusionLimit cashExclusionLimit;

  @JsonProperty("CustomerExtractCode")
  private String customerExtractCode;

  @JsonProperty("TaxIdCertificateCode")
  private String taxIdCertificateCode;

  @JsonProperty("TinActivityDate")
  @Pattern(regexp = EvolutionConstantUtils.REGEX_FOR_DATE, message = EvolutionConstantUtils.DATE_FORMAT_MESSAGE)
  private String tinActivityDate;

  @JsonProperty("TaxIdTypeCode")
  private String taxIdTypeCode;

  @JsonProperty("NumberOfW8OrW9")
  private Integer numberOfW8OrW9;

  @JsonProperty("UserDefinedFlag3")
  private String userDefinedFlag3;

  @JsonProperty("AliasDetails")
  private @Valid AliasDetails aliasDetails;

  @JsonProperty("MemoFlag")
  @Size(max = 1, message =  EvolutionConstantUtils.MEMO_FLAG_SIZE_MESSAGE )
  private String memoFlag;

  @JsonProperty("RequestMemoFlag")
  @Size(max = 1, message = EvolutionConstantUtils.REQUEST_MEMO_FLAG_SIZE_MESSAGE)
  private String requestMemoFlag;

  @JsonProperty("TicklerFlag")
  @Size(max = 1, message = EvolutionConstantUtils.TICKLER_FLAG_SIZE_MESSAGE)
  private String ticklerFlag;

  @JsonProperty("RequestTicklerFlag")
  @Size(max = 1, message = EvolutionConstantUtils.REQUEST_TICKLER_FLAG_SIZE_MESSAGE)
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
  @Size(max = 8, message = EvolutionConstantUtils.TELEX_ANSWER_BACK_NUMBER_SIZE_MESSAGE)
  private String telexAnswerBackNumber;

  @JsonProperty("ActivityLevelCode")
  private String activityLevelCode;

  @JsonProperty("SourceCode")
  @Size(max = 1, message = EvolutionConstantUtils.SOURCE_CODE_SIZE_MESSAGE)
  private String sourceCode;

  @JsonProperty("LifeStageCode")
  private String lifeStageCode;

  @JsonProperty("BehavioralCode")
  private String behavioralCode;

  @JsonProperty("ResponsivenessCode")
  private String responsivenessCode;

  @JsonProperty("ContactPreferenceText")
  @Size(max = 100, message = EvolutionConstantUtils.CONTACT_PREFERENCE_TEXT_SIZE_MESSAGE)
  private String contactPreferenceText;

  @JsonProperty("UserKey")
  private String userKey;

  @JsonProperty("CustomerLinkage")
  @Size(max = 15, message = EvolutionConstantUtils.CUSTOMER_LINKAGE_SIZE_MESSAGE)
  private String customerLinkage;

  @JsonProperty("LoyaltyCardNumber")
  @Size(max = 20, message = EvolutionConstantUtils.LOYALTY_CARD_NUMBER_SIZE_MESSAGE)
  private String loyaltyCardNumber;

  @JsonProperty("AccessCode")
  private String accessCode;

  @JsonProperty("Others")
  private @Valid Others others;
}
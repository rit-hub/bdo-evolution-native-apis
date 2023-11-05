package com.bdo.evolution_native.model.addcard;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AccountDetails
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class AccountDetailsRequest {
    @JsonProperty("CardAccountdetails")
    private @Valid CardAccountdetails cardAccountdetails;

    @JsonProperty("WithdrawlBalanceOverrideFlag")
    private Boolean withdrawlBalanceOverrideFlag;

    @JsonProperty("CardGenerateNextCycleFlag")
    private Boolean cardGenerateNextCycleFlag;

    @JsonProperty("PinMailerNextCycle")
    private String pinMailerNextCycle;

    @JsonProperty("WaiveTransactionFeeFlag")
    private Boolean waiveTransactionFeeFlag;

    @JsonProperty("WaiveCardFeeFlag")
    private Boolean waiveCardFeeFlag;

    @JsonProperty("ThirdPartyTransferFlag")
    private Boolean thirdPartyTransferFlag;

    @JsonProperty("NextCardReissueDate")
    private String nextCardReissueDate;

    @JsonProperty("NextPinIssueDate")
    private String nextPinIssueDate;

    @JsonProperty("NextCardFeeDate")
    private String nextCardFeeDate;

    @JsonProperty("EmbossingName")
    @Size(max = 30, message = EvolutionConstantUtils.EMOSSING_NAME_SIZE_VALIDATION)
    private String embossingName;

    @JsonProperty("RemoteBankingFlag")
    private Boolean remoteBankingFlag;

    @JsonProperty("RequesterUserId")
    private String requesterUserId;

    @JsonProperty("OldCardNumberType")
    private String oldCardNumberType;

    @JsonProperty("CardTypeCode")
    @NotBlank(message = EvolutionConstantUtils.CARDTYPECODE_MANDATORY_VALIDATION)
    @Size(max = 1, message = EvolutionConstantUtils.CARD_TYPE_CODE_SIZE_VALIDATION)
    private String cardTypeCode;

    @JsonProperty("CardStatusCode")
    @Pattern(regexp = "^-?\\d{1}$", message = EvolutionConstantUtils.CARD_STATUS_CODE_SIZE_VALIDATION)
    private String cardStatusCode;

    @JsonProperty("CardExpiryDate")
    private String cardExpiryDate;

    @JsonProperty("ExpirationSpecificDay")
    @Pattern(regexp = "^-?\\d{1}$", message = EvolutionConstantUtils.EXPIRATION_SPECIFIC_DAY_SIZE_VALIDATION)
    private String expirationSpecificDay;

    @NotNull(message = EvolutionConstantUtils.CARDGENERATENUMBER_MANDATORY_VALIDATION)
    @Digits(integer = 2, fraction = 0, message = EvolutionConstantUtils.CARD_GENERATED_NUMBER_SIZE_VALIDATION)
    @JsonProperty("CardGenerateNumber")
    private Integer cardGenerateNumber;
}
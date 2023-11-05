package com.bdo.evolution_native.model.addcard;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;

/**
 * CardFacility
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CardFacility {
    @JsonProperty("CustomerReference")
    private @Valid CustomerReferenceRequest customerReference;

    @JsonProperty("AccountDetails")
    private @Valid AccountDetailsRequest accountDetailsRequest;

    @JsonProperty("LinkedAccounts")
    private @Valid LinkedAccounts linkedAccounts;

    @JsonProperty("BankBranchLocationReference")
    private BankBranchLocationReference bankBranchLocationReference;

    @JsonProperty("AccountCurrency")
    private AccountCurrency accountCurrency;

    @JsonProperty("TaxReference")
    private String taxReference;

    @JsonProperty("EntitlementOptionDefinition")
    private String entitlementOptionDefinition;

    @JsonProperty("EntitlementOptionSetting")
    private String entitlementOptionSetting;

    @JsonProperty("RestrictionOptionDefinition")
    private String restrictionOptionDefinition;

    @JsonProperty("RestrictionOptionSetting")
    private String restrictionOptionSetting;

    @JsonProperty("ConfigurationOptions")
    private String configurationOptions;

    @JsonProperty("CreditCardFacilityOptionSetting")
    private String creditCardFacilityOptionSetting;

    @JsonProperty("Associations")
    private String associations;

    @JsonProperty("AssociationType")
    private String associationType;

    @JsonProperty("AssociationObligationEntitlement")
    private Object associationObligationEntitlement;

    @JsonProperty("AssociationReference")
    private String associationReference;

    @JsonProperty("PositionLimits")
    private String positionLimits;

    @JsonProperty("PositionLimitType")
    private String positionLimitType;

    @JsonProperty("PositionLimitSettings")
    private String positionLimitSettings;

    @JsonProperty("DateType")
    private String dateType;

    @JsonProperty("StatementsSchedule")
    private String statementsSchedule;

    @JsonProperty("StatementType")
    private String statementType;

    @JsonProperty("StatementTransactionType")
    private String statementTransactionType;

    @JsonProperty("StatementPeriod")
    private String statementPeriod;

}

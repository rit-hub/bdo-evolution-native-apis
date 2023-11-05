package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class AccountFacility {
    @JsonProperty("CustomerReference")
    private String customerReference;

    @JsonProperty("BankBranchLocationReference")
    private String bankBranchLocationReference;

    @JsonProperty("AccountType")
    private AccountTypes accountType;

    @JsonProperty("AccountCurrency")
    private AccountCurrency accountCurrency = new AccountCurrency();

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

    @JsonProperty("Associations")
    private String associations;

    @JsonProperty("AssociationType")
    private String associationType;

    @JsonProperty("AssociationObligationEntitlement")
    private String associationObligationEntitlement;

    @JsonProperty("AssociationReference")
    private String associationReference;

    @JsonProperty("LinkedAccounts")
    private Account linkedAccounts;

    @JsonProperty("LinkType")
    private Description linkType = new Description();

    @JsonProperty("AccountDetails")
    private String accountDetails;

    @JsonProperty("PositionLimits")
    private String positionLimits;

    @JsonProperty("PositionLimitType")
    private String positionLimitType;

    @JsonProperty("PositionLimitSettings")
    private String positionLimitSettings;

    @JsonProperty("PositionLimitValue")
    private String positionLimitValue;

    @JsonProperty("DateType")
    private String dateType;

}

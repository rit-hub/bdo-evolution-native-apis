package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * RetrieveTimeDepositFacilityResponseTimeDepositFacility
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class TimeDepositAccountFacility {
    @JsonProperty("ProductInstanceReference")
    private String productInstanceReference;

    @JsonProperty("TimeDepositAccountNumber")
    private String timeDepositAccountNumber;

    @JsonProperty("CustomerReference")
    private String customerReference;

    @JsonProperty("BankBranchLocationReference")
    private String bankBranchLocationReference;

    @JsonProperty("AccountType")
    private String accountType;

    @JsonProperty("AccountCurrency")
    private CurrencyCode accountCurrency = new CurrencyCode();

    @JsonProperty("AccountBalance")
    private List<AccountBalances> accountBalance;

    @JsonProperty("TaxReference")
    private String taxReferences;

    @JsonProperty("EntitlementOptionDefinition")
    private String entitlementOptionDefinitions;

    @JsonProperty("EntitlementOptionSetting")
    private String entitlementOptionSettings;

    @JsonProperty("RestrictionOptionDefinition")
    private String restrictionOptionDefinitions;

    @JsonProperty("RestrictionOptionSetting")
    private String restrictionOptionSettings;

    @JsonProperty("Associations")
    private String association;

    @JsonProperty("AssociationType")
    private String associationTypes;

    @JsonProperty("AssociationObligationEntitlement")
    private String associationObligationEntitlements;

    @JsonProperty("AssociationReference")
    private String associationReferences;

    @JsonProperty("LinkedAccounts")
    private String linkedAccount;

    @JsonProperty("LinkType")
    private String linkTypes;

    @JsonProperty("AccountDetails")
    private String accountDetail;

    @JsonProperty("PositionLimits")
    private String positionLimit;

    @JsonProperty("PositionLimitType")
    private String positionLimitTypes;

    @JsonProperty("PositionLimitSettings")
    private String positionLimitSetting;

    @JsonProperty("PositionLimitValue")
    private String positionLimitValues;

    @JsonProperty("DateType")
    private String dateTypes;

    @JsonProperty("StatementsSchedule")
    private String statementsSchedule;

    @JsonProperty("StatementType")
    private String statementType;

    @JsonProperty("StatementTransactionType")
    private String statementTransactionType;

    @JsonProperty("StatementPeriod")
    private String statementPeriod;

    @JsonProperty("StatementReport")
    private String statementReport;

}

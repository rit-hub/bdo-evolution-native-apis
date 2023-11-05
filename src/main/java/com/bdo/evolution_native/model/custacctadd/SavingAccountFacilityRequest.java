package com.bdo.evolution_native.model.custacctadd;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import java.util.List;

/**
 * SavingAccountFacilityRequest
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class SavingAccountFacilityRequest {
    @JsonProperty("CustomerReference")
    private CustomerReference customerReference;
    @Valid
    @JsonProperty("AccountDetails")
    private AccountDetails accountDetails;
    @Valid
    @JsonProperty("AccountRelations")
    private List<AccountRelations> accountRelations;

    @Valid
    @JsonProperty("TaxReference")
    private TaxReference taxReference;

    @JsonProperty("BankBranchOrLocationReference")
    private Object bankBranchOrLocationReference;

    @JsonProperty("EntitlementOptionSetting")
    private String entitlementOptionSetting;

    @JsonProperty("RestrictionOptionSetting")
    private String restrictionOptionSetting;

    @JsonProperty("AssociationReference")
    private AssociationReference associationReference;

    @JsonProperty("LinkedAccounts")
    private String linkedAccounts;

    @JsonProperty("LinkType")
    private String linkType;

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

    @JsonProperty("StatementReport")
    private String statementReport;

}

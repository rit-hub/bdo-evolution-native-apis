package com.bdo.evolution_native.model.loan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * RetrieveLoanFacilityResponseLoanFacility
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Generated
public class RetrieveLoanFacilityResponseLoanFacility {
    @JsonProperty("ProductInstanceReference")
    private String productInstanceReference;

    @JsonProperty("LoanNumber")
    private String loanNumber;

    @JsonProperty("CustomerReference")
    private String customerReference;

    @JsonProperty("PartyReference")
    private String partyReference;

    @JsonProperty("CustomerAgreementReference")
    private String customerAgreementReference;

    @JsonProperty("CustomerCreditAssessmentReference")
    private String customerCreditAssessmentReference;

    @JsonProperty("InsuranceReference")
    private String insuranceReference;

    @JsonProperty("DelinquencyCollectionReference")
    private String delinquencyCollectionReference;

    @JsonProperty("BankBranchLocationReference")
    private String bankBranchLocationReference;

    @JsonProperty("BankAccountingUnitReference")
    private String bankAccountingUnitReference;

    @JsonProperty("LoanType")
    private String loanType;

    @JsonProperty("AccountBalance")
    private List<Accountbalances> accountBalance;

    @JsonProperty("LoanCurrency")
    private Currencycode loanCurrency = new Currencycode();

    @JsonProperty("LoanRateType")
    private String loanRateType;

    @JsonProperty("LoanApplicableRate")
    private Rate loanApplicableRate = new Rate();

    @JsonProperty("RepaymentType")
    private String repaymentType;

    @JsonProperty("InterestType")
    private String interestType;

    @JsonProperty("InterestAccrualMethod")
    private String interestAccrualMethod;

    @JsonProperty("LoanOriginationDate")
    private Date loanOriginationDate = new Date();

    @JsonProperty("LoanMaturityDate")
    private Date loanMaturityDate = new Date();

    @JsonProperty("CollateralReference")
    private String collateralReference;

    @JsonProperty("CollateralAllocation")
    private String collateralAllocation;

    @JsonProperty("TaxReference")
    private String taxReference;

    @JsonProperty("LoanAccessTerms")
    private String loanAccessTerms;

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

    @JsonProperty("LoanRepaymentSchedule")
    private String loanRepaymentSchedule;

    @JsonProperty("StagedRepaymentStatement")
    private String stagedRepaymentStatement;

    @JsonProperty("CustomerCommentary")
    private String customerCommentary;

    @JsonProperty("LoanOutstandingBalance")
    private String loanOutstandingBalance;

    @JsonProperty("DateType")
    private String dateType;

}

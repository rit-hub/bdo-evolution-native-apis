package com.bdo.evolution_native.model.initiatemodel;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * Others
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@Valid
@SuppressWarnings("CPD-START")
public class Others {
    @JsonProperty("CIFMainBank")
    private String ciFMainBank;


    @JsonProperty("CountryOrigin")
    private String countryOrigin;

    @JsonProperty("CRBureau")
    private String crBureau;

    @JsonProperty("CreditorCode")
    private String creditorCode;

    @JsonProperty("CrossSell")
    private String crossSell;

    @JsonProperty("CrossDef")
    private String crossDef;

    @JsonProperty("CurrentYearDays")
    private String currentYearDays;

    @JsonProperty("CustomerAccountType")
    private String customerAccountType;

    @JsonProperty("CustomerService")
    private String customerService;

    @JsonProperty("LastUpdatedDate")
    @Pattern(regexp = EvolutionConstantUtils.REGEX_FOR_DATE, message = EvolutionConstantUtils.DATE_FORMAT_MESSAGE)
    private String lastUpdatedDate;

    @JsonProperty("DateTagged")
    @Pattern(regexp = EvolutionConstantUtils.REGEX_FOR_DATE, message = EvolutionConstantUtils.DATE_FORMAT_MESSAGE)
    private String dateTagged;

    @JsonProperty("FATCAStat")
    private String faTCAStat;

    @JsonProperty("HoldMail1")
    private String holdMail1;

    @JsonProperty("HoldMail2")
    private String holdMail2;

    @JsonProperty("LastYearDays")
    private String lastYearDays;

    @JsonProperty("OriginatingBranch")
    private String originatingBranch;

    @JsonProperty("Percentage")
    private String percentage;

    @JsonProperty("PowerOfAttorney1")
    private String powerOfAttorney1;

    @JsonProperty("PowerOfAttorney2")
    private String powerOfAttorney2;

    @JsonProperty("ReferenceYear")
    private String referenceYear;

    @JsonProperty("RemarkAction")
    private String remarkAction;

    @JsonProperty("RiskCode")
    private String riskCode;

    @JsonProperty("StandInstruction1")
    private String standInstruction1;

    @JsonProperty("StandInstruction2")
    private String standInstruction2;

    @JsonProperty("SubstantialPresenc")
    private String substantialPresenc;

    @JsonProperty("SubstantialOwner")
    private String substantialOwner;

    @JsonProperty("TwoYearsAgo")
    private String twoYearsAgo;

    @JsonProperty("TwoYearAgoDays")
    private String twoYearAgoDays;

    @JsonProperty("uDFCuusr1")
    private String uDFCuusr1;

    @JsonProperty("uSTIN")
    private String uSTIN;

    @JsonProperty("usaddr1")
    private String usaddr1;

    @JsonProperty("usaddr2")
    private String usaddr2;

    @JsonProperty("usaddr3")
    private String usaddr3;

    @JsonProperty("usaddr4")
    private String usaddr4;

    @JsonProperty("userFld1")
    private String userFld1;

    @JsonProperty("userFld2")
    private String userFld2;

    @JsonProperty("userFld3")
    private String userFld3;

    @JsonProperty("userFld4")
    private String userFld4;

    @JsonProperty("userFld5")
    private String userFld5;

    @JsonProperty("userFld6")
    private String userFld6;

    @JsonProperty("userFld7")
    private String userFld7;

    @JsonProperty("userFld8")
    private String userFld8;

    @JsonProperty("userFld9")
    private String userFld9;

    @JsonProperty("userFld10")
    private String userFld10;

    @JsonProperty("uSPOBox")
    private String uSPOBox;

    @JsonProperty("uSTelNbr")
    private String uSTelNbr;

    @JsonProperty("uSZipCode")
    private String uSZipCode;
}
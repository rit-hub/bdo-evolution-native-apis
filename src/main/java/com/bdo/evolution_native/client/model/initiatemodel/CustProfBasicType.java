package com.bdo.evolution_native.client.model.initiatemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * CustProfBasicType
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@SuppressWarnings("CPD-START")
public class CustProfBasicType {
    @JsonProperty("branchId")
    private String branchId;

    @JsonProperty("shortName")
    private String shortName;

    @JsonProperty("derivedShortName")
    private String derivedShortName;

    @JsonProperty("primOfficerCode")
    private String primOfficerCode;

    @JsonProperty("secondaryOfficerCode")
    private String secondaryOfficerCode;

    @JsonProperty("extraOfficer1Code")
    private String extraOfficer1Code;

    @JsonProperty("extraOfficer2Code")
    private String extraOfficer2Code;

    @JsonProperty("openDt")
    private String openDt;

    @JsonProperty("inqLevelCode")
    private String inqLevelCode;

    @JsonProperty("maintLevelCode")
    private String maintLevelCode;

    @JsonProperty("custLocaleCode")
    private String custLocaleCode;

    @JsonProperty("lastContactDt")
    private String lastContactDt;

    @JsonProperty("lastMaintDt")
    private String lastMaintDt;

    @JsonProperty("custStatusCode")
    private String custStatusCode;

    @JsonProperty("ownHomeCode")
    private String ownHomeCode;

    @JsonProperty("empIncomeAmt")
    private CustProfBasicTypeEmpIncomeAmt empIncomeAmt;

    @JsonProperty("empIncomeSource")
    private String empIncomeSource;

    @JsonProperty("yearsAtJob")
    private Integer yearsAtJob;

    @JsonProperty("professionCode")
    private String professionCode;

    @JsonProperty("empStatus")
    private String empStatus;

    @JsonProperty("busEmailAddr")
    private String busEmailAddr;

    @JsonProperty("personName")
    private PersonFullNameType personName;

    @JsonProperty("custAddr")
    private CustBasicAddrType custAddr;

    @JsonProperty("parsedFlag")
    private String parsedFlag;

    @JsonProperty("custType")
    private String custType;

    @JsonProperty("custProspectCode")
    private String custProspectCode;

    @JsonProperty("pepSanctionFlag")
    private String pepSanctionFlag;

    @JsonProperty("customerCollectionsStatus")
    private String customerCollectionsStatus;

    @JsonProperty("phoneNa")
    private List<PhoneNumTypeWithExtension> phoneNa;

    @JsonProperty("emailAddr")
    private String emailAddr;

    @JsonProperty("genderCode")
    private String genderCode;

    @JsonProperty("raceCode")
    private String raceCode;

    @JsonProperty("taxId")
    private SSNIDType taxId;

    @JsonProperty("sicCode")
    private String sicCode;

    @JsonProperty("birthDt")
    private BirthDateType4 birthDt;

    @JsonProperty("nationalId")
    private NINIDType nationalId;

    @JsonProperty("dependentsNum")
    private Integer dependentsNum;

    @JsonProperty("contactName")
    private String contactName;

    @JsonProperty("contactTitle")
    private String contactTitle;

    @JsonProperty("language")
    private String language;

    @JsonProperty("citizenCode")
    private String citizenCode;

    @JsonProperty("residenceCode")
    private String residenceCode;

    @JsonProperty("withholdingCode")
    private String withholdingCode;

    @JsonProperty("withholdingPercent")
    private BigDecimal withholdingPercent;

    @JsonProperty("taxInfoCode")
    private TINIDType taxInfoCode;

    @JsonProperty("passportId")
    private String passportId;

    @JsonProperty("countryOfBirth")
    private String countryOfBirth;

    @JsonProperty("solicitableCode")
    private String solicitableCode;

    @JsonProperty("accomCode")
    private String accomCode;

    @JsonProperty("socioEconCode")
    private String socioEconCode;

    @JsonProperty("salutation")
    private String salutation;

    @JsonProperty("employeeCode")
    private String employeeCode;

    @JsonProperty("preferredCustCode")
    private String preferredCustCode;

    @JsonProperty("mktSegCode")
    private String mktSegCode;

    @JsonProperty("maritalStatusCode")
    private String maritalStatusCode;

    @JsonProperty("sendMailCode")
    private String sendMailCode;

    @JsonProperty("moveInDt")
    private String moveInDt;

    @JsonProperty("deathDt")
    private String deathDt;

    @JsonProperty("cashExclusionCode")
    private String cashExclusionCode;

    @JsonProperty("cashExclusionLimit")
    private CustProfBasicTypeCashExclusionLimit cashExclusionLimit;

    @JsonProperty("custExtractCode")
    private String custExtractCode;

    @JsonProperty("taxIdCertCode")
    private String taxIdCertCode;

    @JsonProperty("tinActivityDt")
    private String tinActivityDt;

    @JsonProperty("taxIdTypeCode")
    private String taxIdTypeCode;

    @JsonProperty("w8W9Num")
    private Integer w8W9Num;

    @JsonProperty("cardAcctIds")
    private List<CardAcctIdTypeProf> cardAcctIds;

    @JsonProperty("udfCuusr3")
    private String udfCuusr3;

    @JsonProperty("aliasName")
    private PersonFullNameType aliasName;

    @JsonProperty("memoFlag")
    private String memoFlag;

    @JsonProperty("rqMemoFlag")
    private String rqMemoFlag;

    @JsonProperty("ticklerFlag")
    private String ticklerFlag;

    @JsonProperty("rqTicklerFlag")
    private String rqTicklerFlag;

    @JsonProperty("moveInConfCode")
    private String moveInConfCode;

    @JsonProperty("evePhoneAvailCode")
    private String evePhoneAvailCode;

    @JsonProperty("dayPhoneAvailCode")
    private String dayPhoneAvailCode;

    @JsonProperty("custDocCode")
    private String custDocCode;

    @JsonProperty("custDocActivityDt")
    private String custDocActivityDt;

    @JsonProperty("telexNum")
    private String telexNum;

    @JsonProperty("telexAnswerBackNum")
    private String telexAnswerBackNum;

    @JsonProperty("activityLevelCode")
    private String activityLevelCode;

    @JsonProperty("custClassCode")
    private String custClassCode;

    @JsonProperty("sourceCode")
    private String sourceCode;

    @JsonProperty("lifeStageCode")
    private String lifeStageCode;

    @JsonProperty("behavioralCode")
    private String behavioralCode;

    @JsonProperty("responsivenessCode")
    private String responsivenessCode;

    @JsonProperty("contactPrefText")
    private String contactPrefText;

    @JsonProperty("userKey")
    private String userKey;

    @JsonProperty("custMailAcctCode")
    private String custMailAcctCode;

    @JsonProperty("custMailCustCode")
    private String custMailCustCode;

    @JsonProperty("custLinkage")
    private String custLinkage;

    @JsonProperty("loyaltyCardNum")
    private String loyaltyCardNum;

    @JsonProperty("accessCode")
    private String accessCode;

    @JsonProperty("custDlrDlrGroupFlag")
    private String custDlrDlrGroupFlag;

    @JsonProperty("vatExemptionCode")
    private String vatExemptionCode;

    @JsonProperty("acctTitle")
    private String acctTitle;

    @JsonProperty("affectTaxYrCode")
    private String affectTaxYrCode;

    @JsonProperty("bDOEmpNbr")
    private String bDOEmpNbr;

    @JsonProperty("birthTownCity")
    private String birthTownCity;

    @JsonProperty("birthProvince")
    private String birthProvince;

    @JsonProperty("cardIssuance")
    private String cardIssuance;

    @JsonProperty("cifmainBank")
    private String cifmainBank;

    @JsonProperty("countryOrigin")
    private String countryOrigin;

    @JsonProperty("cRBureau")
    private String cRBureau;

    @JsonProperty("creditorCode")
    private String creditorCode;

    @JsonProperty("crossSell")
    private String crossSell;

    @JsonProperty("crossDef")
    private String crossDef;

    @JsonProperty("currYearDays")
    private String currYearDays;

    @JsonProperty("custAcctTyp")
    private String custAcctTyp;

    @JsonProperty("custService")
    private String custService;

    @JsonProperty("dateLastUpdate")
    private String dateLastUpdate;

    @JsonProperty("dateTagged")
    private String dateTagged;

    @JsonProperty("fATCAStat")
    private String fATCAStat;

    @JsonProperty("fatherName")
    private String fatherName;

    @JsonProperty("holdMail1")
    private String holdMail1;

    @JsonProperty("holdMail2")
    private String holdMail2;

    @JsonProperty("lastYear")
    private String lastYear;

    @JsonProperty("lastYearDays")
    private String lastYearDays;

    @JsonProperty("motherName")
    private String motherName;

    @JsonProperty("natureWrkEmployed")
    private String natureWrkEmployed;

    @JsonProperty("natureWrkSelf")
    private String natureWrkSelf;

    @JsonProperty("originatingBranch")
    private String originatingBranch;

    @JsonProperty("percentage")
    private String percentage;

    @JsonProperty("permAddr1")
    private String permAddr1;

    @JsonProperty("permAddr2")
    private String permAddr2;

    @JsonProperty("permAddr3")
    private String permAddr3;

    @JsonProperty("permAddr4")
    private String permAddr4;

    @JsonProperty("permAddr5")
    private String permAddr5;

    @JsonProperty("permZipCode")
    private String permZipCode;

    @JsonProperty("permZipCodeSuff")
    private String permZipCodeSuff;

    @JsonProperty("powerAtty1")
    private String powerAtty1;

    @JsonProperty("powerAtty2")
    private String powerAtty2;

    @JsonProperty("refYear")
    private String refYear;

    @JsonProperty("remarkAction")
    private String remarkAction;

    @JsonProperty("riskCode")
    private String riskCode;

    @JsonProperty("spouseFirstName")
    private String spouseFirstName;

    @JsonProperty("spouseMidName")
    private String spouseMidName;

    @JsonProperty("spouseLastName")
    private String spouseLastName;

    @JsonProperty("spouseBirth")
    private String spouseBirth;

    @JsonProperty("spousePlaceBirth")
    private String spousePlaceBirth;

    @JsonProperty("spouseWork")
    private String spouseWork;

    @JsonProperty("standInstr1")
    private String standInstr1;

    @JsonProperty("standInstr2")
    private String standInstr2;

    @JsonProperty("substPres")
    private String substPres;

    @JsonProperty("substOwner")
    private String substOwner;

    @JsonProperty("twoYearsAgo")
    private String twoYearsAgo;

    @JsonProperty("twoYearAgoDays")
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

    @JsonProperty("reSubmit")
    private String reSubmit;

    @JsonProperty("countryBirth")
    private String countryBirth;
}
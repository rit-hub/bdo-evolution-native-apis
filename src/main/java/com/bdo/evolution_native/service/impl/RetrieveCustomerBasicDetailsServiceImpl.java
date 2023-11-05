package com.bdo.evolution_native.service.impl;

import com.bdo.evolution_native.client.RetrieveCustomerBasicDetailsClient;
import com.bdo.evolution_native.client.model.initiatemodel.*;
import com.bdo.evolution_native.client.model.retrivemodel.CustProfBasicInqRq;
import com.bdo.evolution_native.client.model.retrivemodel.CustProfBasicInqRs;
import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.bdo.evolution_native.model.Links;
import com.bdo.evolution_native.model.Meta;
import com.bdo.evolution_native.model.initiatemodel.Others;
import com.bdo.evolution_native.model.retrivemodel.*;
import com.bdo.evolution_native.service.RetrieveCustomerBasicDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class RetrieveCustomerBasicDetailsServiceImpl implements RetrieveCustomerBasicDetailsService {
    @Autowired
    private RetrieveCustomerBasicDetailsClient client;

    @Override
    public Mono<RetrieveCustomerResponse> retriveCustomer(
            final String customerNumber, final ServletUriComponentsBuilder servlet) {
        final CustProfBasicInqRq custProfBasicInqRq =
                CustProfBasicInqRq.builder().custId(CustIdType.builder()
                        .custPermId(customerNumber).build()).build();
        final Mono<CustProfBasicInqRs> sorResponse = client.retriveCustomerClient(custProfBasicInqRq);
        return sorResponse.map(response -> RetrieveCustomerResponse.builder()
                .data(convertSorPayloadToMsDataPayload(response))
                .meta(Meta.builder()
                        .totalPages(EvolutionConstantUtils.ONE)
                        .firstAvailableDateTime(EvolutionConstantUtils.EMPTY_STRING)
                        .lastAvailableDateTime(EvolutionConstantUtils.EMPTY_STRING)
                        .build()).links(Links.builder()
                        .first(EvolutionConstantUtils.EMPTY_STRING)
                        .last(EvolutionConstantUtils.EMPTY_STRING)
                        .next(EvolutionConstantUtils.EMPTY_STRING)
                        .prev(EvolutionConstantUtils.EMPTY_STRING)
                        .self(servlet.build().toString())
                        .build()).build());
    }

    private RetrieveCustomerResponseData convertSorPayloadToMsDataPayload(
            final CustProfBasicInqRs sorResponse) {
        setDefaultValuesIfNull(sorResponse);
        return RetrieveCustomerResponseData.builder().customerProfileDetails(
                CustomerProfileDetails.builder()
                        .requestId(sorResponse.getRqUID().getRequestUid())
                        .cardAccountDetails(getCardAccountDetails(sorResponse))
                        .motoAccountType(getMotoAccountType(sorResponse))
                        .customerProfileBasic(getCustomerProfileBasic(sorResponse))
                        .employmentDetails(getEmploymentDetails(sorResponse))
                        .personDetails(getPersonDetails(sorResponse))
                        .customerAddressDetail(getCustomerAddressDetail(sorResponse))
                        .parsedFlag(sorResponse.getCustProfBasic().getParsedFlag())
                        .sanctionFlag(sorResponse.getCustProfBasic().getPepSanctionFlag())
                        .phoneNumberDetails(List.of(getPhoneNumber(sorResponse)))
                        .emailAddress(sorResponse.getCustProfBasic().getEmailAddr())
                        .genderCode(sorResponse.getCustProfBasic().getGenderCode())
                        .raceCode(sorResponse.getCustProfBasic().getRaceCode())
                        .taxDetails(TaxDetails.builder()
                                .taxId(sorResponse.getCustProfBasic().getTaxId().getId())
                                .vatExemptionCode(sorResponse.getCustProfBasic().getVatExemptionCode())
                                .affectedTaxYearCode(sorResponse.getCustProfBasic().getAffectTaxYrCode())
                                .build())
                        .sicCode(sorResponse.getCustProfBasic().getSicCode())
                        .customerDetails(getCustomerDetails(sorResponse))
                        .cashExclusionCode(sorResponse.getCustProfBasic().getCashExclusionCode())
                        .cashExclusionLimit(CashExclusionLimit.builder()
                                .amount(sorResponse.getCustProfBasic().getCashExclusionLimit().getAmt())
                                .build())
                        .customerExtractCode(sorResponse.getCustProfBasic().getCustExtractCode())
                        .taxIdCertificateCode(sorResponse.getCustProfBasic().getTaxIdCertCode())
                        .tinActivityDate(sorResponse.getCustProfBasic().getTinActivityDt())
                        .taxIdTypeCode(sorResponse.getCustProfBasic().getTaxIdTypeCode())
                        .numberOfW8OrW9(sorResponse.getCustProfBasic().getW8W9Num())
                        .userDefinedFlag3(sorResponse.getCustProfBasic().getUdfCuusr3())
                        .aliasDetails(AliasDetails.builder()
                                .lastName(sorResponse.getCustProfBasic().getAliasName().getLastName())
                                .firstName(sorResponse.getCustProfBasic().getAliasName().getFirstName())
                                .middleName(sorResponse.getCustProfBasic().getAliasName().getMiddleName())
                                .secondSurname(sorResponse.getCustProfBasic().getAliasName().getSecondSurname())
                                .titlePrefix(sorResponse.getCustProfBasic().getAliasName().getTitlePrefix())
                                .nameSuffix(sorResponse.getCustProfBasic().getAliasName().getNameSuffix())
                                .businessName(sorResponse.getCustProfBasic().getAliasName().getBusinessName())
                                .build())
                        .memoFlag(sorResponse.getCustProfBasic().getMemoFlag())
                        .requestMemoFlag(sorResponse.getCustProfBasic().getRqMemoFlag())
                        .ticklerFlag(sorResponse.getCustProfBasic().getTicklerFlag())
                        .requestTicklerFlag(sorResponse.getCustProfBasic().getRqTicklerFlag())
                        .moveInConferenceCode(sorResponse.getCustProfBasic().getMoveInConfCode())
                        .eveningPhoneAvailabilityCode(sorResponse.getCustProfBasic().getEvePhoneAvailCode())
                        .dayPhoneAvailabilityCode(sorResponse.getCustProfBasic().getDayPhoneAvailCode())
                        .ticklerNumber(sorResponse.getCustProfBasic().getTelexNum())
                        .telexAnswerBackNumber(sorResponse.getCustProfBasic().getTelexAnswerBackNum())
                        .activityLevelCode(sorResponse.getCustProfBasic().getActivityLevelCode())
                        .sourceCode(sorResponse.getCustProfBasic().getSourceCode())
                        .lifeStageCode(sorResponse.getCustProfBasic().getLifeStageCode())
                        .behavioralCode(sorResponse.getCustProfBasic().getBehavioralCode())
                        .responsivenessCode(sorResponse.getCustProfBasic().getResponsivenessCode())
                        .contactPreferenceText(sorResponse.getCustProfBasic().getContactPrefText())
                        .userKey(sorResponse.getCustProfBasic().getUserKey())
                        .customerLinkage(sorResponse.getCustProfBasic().getCustLinkage())
                        .loyaltyCardNumber(sorResponse.getCustProfBasic().getLoyaltyCardNum())
                        .accessCode(sorResponse.getCustProfBasic().getAccessCode())
                        .others(Others.builder()
                                .ciFMainBank(sorResponse.getCustProfBasic().getCifmainBank())
                                .countryOrigin(sorResponse.getCustProfBasic().getCountryOrigin())
                                .crBureau(sorResponse.getCustProfBasic().getCRBureau())
                                .creditorCode(sorResponse.getCustProfBasic().getCreditorCode())
                                .crossSell(sorResponse.getCustProfBasic().getCrossSell())
                                .crossDef(sorResponse.getCustProfBasic().getCrossDef())
                                .currentYearDays(sorResponse.getCustProfBasic().getCurrYearDays())
                                .customerAccountType(sorResponse.getCustProfBasic().getCustAcctTyp())
                                .customerService(sorResponse.getCustProfBasic().getCustService())
                                .lastUpdatedDate(sorResponse.getCustProfBasic().getDateLastUpdate())
                                .dateTagged(sorResponse.getCustProfBasic().getDateTagged())
                                .faTCAStat(sorResponse.getCustProfBasic().getFATCAStat())
                                .holdMail1(sorResponse.getCustProfBasic().getHoldMail1())
                                .holdMail2(sorResponse.getCustProfBasic().getHoldMail2())
                                .lastYearDays(sorResponse.getCustProfBasic().getLastYearDays())
                                .originatingBranch(sorResponse.getCustProfBasic().getOriginatingBranch())
                                .percentage(sorResponse.getCustProfBasic().getPercentage())
                                .powerOfAttorney1(sorResponse.getCustProfBasic().getPowerAtty1())
                                .powerOfAttorney2(sorResponse.getCustProfBasic().getPowerAtty2())
                                .referenceYear(sorResponse.getCustProfBasic().getRefYear())
                                .remarkAction(sorResponse.getCustProfBasic().getRemarkAction())
                                .riskCode(sorResponse.getCustProfBasic().getRiskCode())
                                .standInstruction1(sorResponse.getCustProfBasic().getStandInstr1())
                                .standInstruction2(sorResponse.getCustProfBasic().getStandInstr2())
                                .substantialOwner(sorResponse.getCustProfBasic().getSubstOwner())
                                .substantialPresenc(sorResponse.getCustProfBasic().getSubstPres())
                                .twoYearAgoDays(sorResponse.getCustProfBasic().getTwoYearAgoDays())
                                .twoYearsAgo(sorResponse.getCustProfBasic().getTwoYearsAgo())
                                .uDFCuusr1(sorResponse.getCustProfBasic().getUDFCuusr1())
                                .uSTIN(sorResponse.getCustProfBasic().getUSTIN())
                                .usaddr1(sorResponse.getCustProfBasic().getUsaddr1())
                                .usaddr2(sorResponse.getCustProfBasic().getUsaddr2())
                                .usaddr3(sorResponse.getCustProfBasic().getUsaddr3())
                                .usaddr4(sorResponse.getCustProfBasic().getUsaddr4())
                                .userFld1(sorResponse.getCustProfBasic().getUserFld1())
                                .userFld2(sorResponse.getCustProfBasic().getUserFld2())
                                .userFld3(sorResponse.getCustProfBasic().getUserFld3())
                                .userFld4(sorResponse.getCustProfBasic().getUserFld4())
                                .userFld5(sorResponse.getCustProfBasic().getUserFld5())
                                .userFld6(sorResponse.getCustProfBasic().getUserFld6())
                                .userFld7(sorResponse.getCustProfBasic().getUserFld7())
                                .userFld8(sorResponse.getCustProfBasic().getUserFld8())
                                .userFld9(sorResponse.getCustProfBasic().getUserFld9())
                                .userFld10(sorResponse.getCustProfBasic().getUserFld10())
                                .uSPOBox(sorResponse.getCustProfBasic().getUSPOBox())
                                .uSTelNbr(sorResponse.getCustProfBasic().getUSTelNbr())
                                .uSZipCode(sorResponse.getCustProfBasic().getUSZipCode())
                                .build())
                        .build()).build();
    }

    private static CustomerDetails getCustomerDetails(final CustProfBasicInqRs sorResponse) {
        return CustomerDetails.builder()
                .birthDetails(BirthDetails.builder()
                        .birthDate(sorResponse.getCustProfBasic().getBirthDt().getDate())
                        .cityOfBirth(sorResponse.getCustProfBasic().getBirthTownCity())
                        .birthProvince(sorResponse.getCustProfBasic().getBirthProvince())
                        .countryOfBirth(sorResponse.getCustProfBasic().getCountryOfBirth())
                        .build())
                .nationalDetails(NationalDetails.builder()
                        .nationalId(sorResponse.getCustProfBasic().getNationalId().getId())
                        .build())
                .numberOfDependents(sorResponse.getCustProfBasic().getDependentsNum())
                .contactName(sorResponse.getCustProfBasic().getContactName())
                .contactTitle(sorResponse.getCustProfBasic().getContactTitle())
                .fathersName(sorResponse.getCustProfBasic().getFatherName())
                .mothersName(sorResponse.getCustProfBasic().getMotherName())
                .spouseDetails(SpouseDetails.builder()
                        .firstName(sorResponse.getCustProfBasic().getSpouseFirstName())
                        .lastName(sorResponse.getCustProfBasic().getSpouseLastName())
                        .middleName(sorResponse.getCustProfBasic().getSpouseMidName())
                        .dateOfBirth(sorResponse.getCustProfBasic().getSpouseBirth())
                        .placeOfBirth(sorResponse.getCustProfBasic().getSpousePlaceBirth())
                        .workDetails(sorResponse.getCustProfBasic().getSpouseWork())
                        .build())
                .language(sorResponse.getCustProfBasic().getLanguage())
                .citizenCode(sorResponse.getCustProfBasic().getCitizenCode())
                .residenceCode(sorResponse.getCustProfBasic().getResidenceCode())
                .withholdingCode(sorResponse.getCustProfBasic().getWithholdingCode())
                .withholdingPercent(sorResponse.getCustProfBasic().getWithholdingPercent())
                .passportId(sorResponse.getCustProfBasic().getPassportId())
                .countryOfBirth(sorResponse.getCustProfBasic().getCountryOfBirth())
                .solicitableCode(sorResponse.getCustProfBasic().getSolicitableCode())
                .accommodationCode(sorResponse.getCustProfBasic().getAccomCode())
                .socioEconomicCode(sorResponse.getCustProfBasic().getSocioEconCode())
                .salutation(sorResponse.getCustProfBasic().getSalutation())
                .preferredCustomerCode(sorResponse.getCustProfBasic().getPreferredCustCode())
                .marketSegmentCode(sorResponse.getCustProfBasic().getMktSegCode())
                .maritalStatusCode(sorResponse.getCustProfBasic().getMaritalStatusCode())
                .sendMailCode(sorResponse.getCustProfBasic().getSendMailCode())
                .moveInDate(sorResponse.getCustProfBasic().getMoveInDt())
                .dateOfDeath(sorResponse.getCustProfBasic().getDeathDt())
                .customerDocumentCode(sorResponse.getCustProfBasic().getCustDocCode())
                .customerDocumentActivityDate(sorResponse.getCustProfBasic().getCustDocActivityDt())
                .customerMailAccountCode(sorResponse.getCustProfBasic().getCustMailAcctCode())
                .customerMailCustomerCode(sorResponse.getCustProfBasic().getCustMailCustCode())
                .customerDealerGroupFlag(sorResponse.getCustProfBasic().getCustDlrDlrGroupFlag())
                .customerClassCode(sorResponse.getCustProfBasic().getCustClassCode())
                .customerType(sorResponse.getCustProfBasic().getCustType())
                .customerProspectCode(sorResponse.getCustProfBasic().getCustProspectCode())
                .customerCollectionStatus(sorResponse.getCustProfBasic().getCustomerCollectionsStatus())
                .build();
    }

    private static PhoneNumber getPhoneNumber(final CustProfBasicInqRs sorResponse) {
        return PhoneNumber.builder()
                .phoneType(sorResponse.getCustProfBasic().getPhoneNa()
                        .get(EvolutionConstantUtils.ZERO).getPhoneType())
                .phone(sorResponse.getCustProfBasic().getPhoneNa().get(EvolutionConstantUtils.ZERO).getPhone())
                .initialDialCode(sorResponse.getCustProfBasic().getPhoneNa()
                        .get(EvolutionConstantUtils.ZERO).getIntlDialCode())
                .extension(sorResponse.getCustProfBasic().getPhoneNa().get(EvolutionConstantUtils.ZERO)
                        .getPhoneExt())
                .build();
    }

    private static CustomerAddressDetail getCustomerAddressDetail(final CustProfBasicInqRs sorResponse) {
        return CustomerAddressDetail.builder()
                .fullName(sorResponse.getCustProfBasic().getCustAddr().getFullName())
                .line1(sorResponse.getCustProfBasic().getCustAddr().getAddr1())
                .line2(sorResponse.getCustProfBasic().getCustAddr().getAddr2())
                .line3(sorResponse.getCustProfBasic().getCustAddr().getAddr3())
                .line4(sorResponse.getCustProfBasic().getCustAddr().getAddr4())
                .region(EvolutionConstantUtils.EMPTY_STRING)
                .province(sorResponse.getCustProfBasic().getCustAddr().getStateProv())
                .postalCode(sorResponse.getCustProfBasic().getCustAddr().getPostLocaleCode())
                .country(sorResponse.getCustProfBasic().getCustAddr().getCountry())
                .build();
    }

    private static PersonDetails getPersonDetails(final CustProfBasicInqRs sorResponse) {
        return PersonDetails.builder()
                .lastName(sorResponse.getCustProfBasic().getPersonName().getLastName())
                .firstName(sorResponse.getCustProfBasic().getPersonName().getFirstName())
                .middleName(sorResponse.getCustProfBasic().getPersonName().getMiddleName())
                .titlePrefix(sorResponse.getCustProfBasic().getPersonName().getTitlePrefix())
                .nameSuffix(sorResponse.getCustProfBasic().getPersonName().getNameSuffix())
                .businessName(sorResponse.getCustProfBasic().getPersonName().getBusinessName())
                .build();
    }

    private static CustomerProfileBasic getCustomerProfileBasic(final CustProfBasicInqRs sorResponse) {
        return CustomerProfileBasic.builder()
                .customerId(
                        CustomerId.builder().customerNumber(sorResponse.getCustId().getCustPermId())
                                .build())
                .branchId(sorResponse.getCustProfBasic().getBranchId())
                .branchName(sorResponse.getCustProfBasic().getCardAcctIds().get(EvolutionConstantUtils.ZERO)
                        .getBankInfo().getBranchName())
                .shortName(sorResponse.getCustProfBasic().getShortName())
                .derivedShortName(sorResponse.getCustProfBasic().getDerivedShortName())
                .primaryOfficerCode(sorResponse.getCustProfBasic().getPrimOfficerCode())
                .secondaryOfficerCode(sorResponse.getCustProfBasic().getSecondaryOfficerCode())
                .extraOfficerCode1(sorResponse.getCustProfBasic().getExtraOfficer1Code())
                .extraOfficerCode2(sorResponse.getCustProfBasic().getExtraOfficer2Code())
                .openDate(sorResponse.getCustProfBasic().getOpenDt())
                .inquiryLevelCode(sorResponse.getCustProfBasic().getInqLevelCode())
                .maintenanceLevelCode(sorResponse.getCustProfBasic().getMaintLevelCode())
                .customerLocaleCode(sorResponse.getCustProfBasic().getCustLocaleCode())
                .lastContactDate(sorResponse.getCustProfBasic().getLastContactDt())
                .lastMaintenanceDate(sorResponse.getCustProfBasic().getLastMaintDt())
                .customerStatusCode(sorResponse.getCustProfBasic().getCustStatusCode())
                .ownHomeCode(sorResponse.getCustProfBasic().getOwnHomeCode())
                .build();
    }

    private static EmploymentDetails getEmploymentDetails(final CustProfBasicInqRs sorResponse) {
        return EmploymentDetails.builder()
                .employeeIncomeDetails(EmployeeIncomeDetails.builder()
                        .employeeIncomeAmount(sorResponse.getCustProfBasic().getEmpIncomeAmt().getAmt())
                        .employmentIncomeSource(sorResponse.getCustProfBasic().getEmpIncomeSource())
                        .build())
                .yearsAtJob(sorResponse.getCustProfBasic().getYearsAtJob())
                .professionCode(sorResponse.getCustProfBasic().getProfessionCode())
                .employmentStatus(sorResponse.getCustProfBasic().getEmpStatus())
                .businessEmailAddress(sorResponse.getCustProfBasic().getBusEmailAddr())
                .employeeCode(sorResponse.getCustProfBasic().getEmployeeCode())
                .bdOEmployeeNumber(sorResponse.getCustProfBasic().getBDOEmpNbr())
                .natureOfWorkEmployed(sorResponse.getCustProfBasic().getNatureWrkEmployed())
                .natureOfWorkSelf(sorResponse.getCustProfBasic().getNatureWrkSelf())
                .build();
    }

    private static MotoAccountType getMotoAccountType(final CustProfBasicInqRs sorResponse) {
        return MotoAccountType.builder()
                .expiryDate(sorResponse.getCustProfBasic().getCardAcctIds().get(EvolutionConstantUtils.ZERO)
                        .getCcMotoAcctType().getExpDt())
                .build();
    }

    private static CardAccountDetails getCardAccountDetails(final CustProfBasicInqRs sorResponse) {
        return CardAccountDetails.builder()
                .accountTitle(sorResponse.getCustProfBasic().getAcctTitle())
                .accountType(sorResponse.getCardAcctId().getAcctType())
                .accountId(sorResponse.getCardAcctId().getAcctId())
                .cardHolderFlag(sorResponse.getCustProfBasic()
                                .getCardAcctIds().get(EvolutionConstantUtils.ZERO).getCardHolderFlag())
                .cardIssuance(sorResponse.getCustProfBasic().getCardIssuance())
                .build();
    }

    private void setDefaultValuesIfNull(final CustProfBasicInqRs sorResponse) {
        sorResponse.setRqUID(Optional.ofNullable(sorResponse.getRqUID()).orElseGet(RqUID::new));
        sorResponse.setCustProfBasic(Optional.ofNullable(sorResponse.getCustProfBasic())
            .orElseGet(CustProfBasicType::new));
        sorResponse.setCustId(Optional.ofNullable(sorResponse.getCustId()).orElseGet(CustIdType::new));
        sorResponse.setCardAcctId(Optional.ofNullable(sorResponse.getCardAcctId()).orElseGet(CardAcctIdType::new));

        final CustProfBasicType custProfBasic = sorResponse.getCustProfBasic();

        custProfBasic.setTaxId(Optional.ofNullable(custProfBasic.getTaxId()).orElseGet(SSNIDType::new));
        custProfBasic.setCashExclusionLimit(Optional.ofNullable(custProfBasic.getCashExclusionLimit())
            .orElseGet(CustProfBasicTypeCashExclusionLimit::new));
        custProfBasic.setAliasName(Optional.ofNullable(custProfBasic.getAliasName())
            .orElseGet(PersonFullNameType::new));
        custProfBasic.setPersonName(Optional.ofNullable(custProfBasic.getPersonName())
            .orElseGet(PersonFullNameType::new));
        custProfBasic.setBirthDt(Optional.ofNullable(custProfBasic.getBirthDt()).orElseGet(BirthDateType4::new));
        custProfBasic.setNationalId(Optional.ofNullable(custProfBasic.getNationalId()).orElseGet(NINIDType::new));

        // Handle collections
        custProfBasic.setPhoneNa(Optional.ofNullable(custProfBasic.getPhoneNa())
            .orElseGet(() -> List.of(new PhoneNumTypeWithExtension())));
        custProfBasic.setCustAddr(Optional.ofNullable(custProfBasic.getCustAddr())
            .orElseGet(com.bdo.evolution_native.client.model.initiatemodel.CustBasicAddrType::new));
        custProfBasic.setEmpIncomeAmt(Optional.ofNullable(custProfBasic.getEmpIncomeAmt())
            .orElseGet(CustProfBasicTypeEmpIncomeAmt::new));

        // Handle collections within collections
        custProfBasic.setCardAcctIds(Optional.ofNullable(custProfBasic.getCardAcctIds())
            .orElseGet(() -> List.of(new CardAcctIdTypeProf())));
        custProfBasic.getCardAcctIds().get(EvolutionConstantUtils.ZERO)
                .setCcMotoAcctType(Optional.ofNullable(custProfBasic.getCardAcctIds().get(EvolutionConstantUtils.ZERO)
                    .getCcMotoAcctType()).orElseGet(CCMotoAcctType::new));
        custProfBasic.getCardAcctIds().get(EvolutionConstantUtils.ZERO)
                .setBankInfo(Optional.ofNullable(custProfBasic.getCardAcctIds().get(EvolutionConstantUtils.ZERO)
                    .getBankInfo()).orElseGet(BankInfoType::new));
    }

}

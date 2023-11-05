package com.bdo.evolution_native.service.impl;

import com.bdo.evolution_native.client.InitiateCustomerBasicDetailsClient;
import com.bdo.evolution_native.client.model.initiatemodel.*;
import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.bdo.evolution_native.exception.RecordNotFoundException;
import com.bdo.evolution_native.model.Links;
import com.bdo.evolution_native.model.Meta;
import com.bdo.evolution_native.model.initiatemodel.*;
import com.bdo.evolution_native.service.InitiateCustomerBasicDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class InitiateCustomerBasicDetailsServiceImpl implements InitiateCustomerBasicDetailsService {
    @Autowired
    private InitiateCustomerBasicDetailsClient client;

    @Override
    public Mono<AddCustomerResponse> initiateCustomer(
        final AddCustomerRequest request, final ServletUriComponentsBuilder servlet) {

        final Mono<CustProfBasicAddRs> custProfBasicAddRs = client.initiateCustomerClient(
            mapToCustProfBasicAddRq(request));
        if (Objects.isNull(custProfBasicAddRs)) {
            throw new RecordNotFoundException(EvolutionConstantUtils.SOR_NOT_FOUND, new Throwable());
        }
        return custProfBasicAddRs.map(response -> mapToAddCustomerResponse(response, servlet));
    }

    private CustProfBasicAddRq mapToCustProfBasicAddRq(
        final AddCustomerRequest request) {
        return CustProfBasicAddRq.builder()
            .rqUID(RqUID.builder()
                .requestUid(request.getCustomerProfileDetails().getRequestId())
                .build())
            .custProfBasic(getCustProfBasicType(request))
            .custProfBasic(CustProfBasicType.builder()
                .cardAcctIds(getCardAcctIds(request))
                .cardIssuance(request.getCustomerProfileDetails().getCardAccountDetails().getCardIssuance())
                .branchId(request.getCustomerProfileDetails().getCustomerProfileBasic().getBranchId())
                .shortName(request.getCustomerProfileDetails().getCustomerProfileBasic().getShortName())
                .derivedShortName(request.getCustomerProfileDetails().getCustomerProfileBasic().getDerivedShortName())
                .primOfficerCode(request.getCustomerProfileDetails().getCustomerProfileBasic().getPrimaryOfficerCode())
                .secondaryOfficerCode(request.getCustomerProfileDetails()
                    .getCustomerProfileBasic().getSecondaryOfficerCode())
                .extraOfficer1Code(request.getCustomerProfileDetails().getCustomerProfileBasic().getExtraOfficerCode1())
                .extraOfficer2Code(request.getCustomerProfileDetails().getCustomerProfileBasic().getExtraOfficerCode2())
                .openDt(request.getCustomerProfileDetails().getCustomerProfileBasic().getOpenDate())
                .inqLevelCode(request.getCustomerProfileDetails().getCustomerProfileBasic().getInquiryLevelCode())
                .maintLevelCode(request.getCustomerProfileDetails().getCustomerProfileBasic().getMaintenanceLevelCode())
                .custLocaleCode(request.getCustomerProfileDetails().getCustomerProfileBasic().getCustomerLocaleCode())
                .lastContactDt(request.getCustomerProfileDetails().getCustomerProfileBasic().getLastContactDate())
                .lastMaintDt(request.getCustomerProfileDetails().getCustomerProfileBasic().getLastMaintenanceDate())
                .custStatusCode(request.getCustomerProfileDetails().getCustomerProfileBasic().getCustomerStatusCode())
                .ownHomeCode(request.getCustomerProfileDetails().getCustomerProfileBasic().getOwnHomeCode())
                .empIncomeAmt(getEmpIncomeAmt(request))
                .empIncomeSource(request.getCustomerProfileDetails().getEmploymentDetails()
                    .getEmployeeIncomeDetails().getEmploymentIncomeSource())
                .yearsAtJob(request.getCustomerProfileDetails().getEmploymentDetails().getYearsAtJob())
                .professionCode(request.getCustomerProfileDetails().getEmploymentDetails().getProfessionCode())
                .empStatus(request.getCustomerProfileDetails().getEmploymentDetails().getEmploymentStatus())
                .busEmailAddr(request.getCustomerProfileDetails().getEmploymentDetails().getBusinessEmailAddress())
                .employeeCode(request.getCustomerProfileDetails().getEmploymentDetails().getEmployeeCode())
                .bDOEmpNbr(request.getCustomerProfileDetails().getEmploymentDetails().getBdOEmployeeNumber())
                .natureWrkEmployed(request.getCustomerProfileDetails().getEmploymentDetails().getNatureOfWorkEmployed())
                .natureWrkSelf(request.getCustomerProfileDetails().getEmploymentDetails().getNatureOfWorkSelf())
                .personName(getPersonName(request))
                .custAddr(getBuild(request))
                .parsedFlag(request.getCustomerProfileDetails().getParsedFlag())
                .pepSanctionFlag(request.getCustomerProfileDetails().getSanctionFlag())
                .phoneNa(getPhoneNa(request))
                .emailAddr(request.getCustomerProfileDetails().getEmailAddress())
                .genderCode(request.getCustomerProfileDetails().getGenderCode())
                .raceCode(request.getCustomerProfileDetails().getRaceCode())
                .taxId(getTaxId(request))
                .vatExemptionCode(request.getCustomerProfileDetails().getTaxDetails().getVatExemptionCode())
                .affectTaxYrCode(request.getCustomerProfileDetails().getTaxDetails().getAffectedTaxYearCode())
                .sicCode(request.getCustomerProfileDetails().getSicCode())
                .birthDt(getBirthDt(request))
                .birthTownCity(request.getCustomerProfileDetails()
                    .getCustomerDetails().getBirthDetails().getCityOfBirth())
                .birthProvince(request.getCustomerProfileDetails()
                    .getCustomerDetails().getBirthDetails().getBirthProvince())
                .countryOfBirth(request.getCustomerProfileDetails()
                    .getCustomerDetails().getBirthDetails().getCountryOfBirth())
                .nationalId(NINIDType.builder()
                    .id(request.getCustomerProfileDetails().getCustomerDetails()
                        .getNationalDetails().getNationalId())
                    .build())
                .dependentsNum(request.getCustomerProfileDetails().getCustomerDetails().getNumberOfDependents())
                .contactName(request.getCustomerProfileDetails().getCustomerDetails().getContactName())
                .contactTitle(request.getCustomerProfileDetails().getCustomerDetails().getContactTitle())
                .fatherName(request.getCustomerProfileDetails().getCustomerDetails().getFathersName())
                .motherName(request.getCustomerProfileDetails().getCustomerDetails().getMothersName())
                .spouseFirstName(request.getCustomerProfileDetails().getCustomerDetails()
                    .getSpouseDetails().getFirstName())
                .spouseMidName(request.getCustomerProfileDetails().getCustomerDetails()
                    .getSpouseDetails().getMiddleName())
                .spouseLastName(request.getCustomerProfileDetails().getCustomerDetails()
                    .getSpouseDetails().getLastName())
                .spouseBirth(request.getCustomerProfileDetails().getCustomerDetails()
                    .getSpouseDetails().getDateOfBirth())
                .spousePlaceBirth(request.getCustomerProfileDetails().getCustomerDetails()
                    .getSpouseDetails().getPlaceOfBirth())
                .spouseWork(request.getCustomerProfileDetails().getCustomerDetails()
                    .getSpouseDetails().getWorkDetails())
                .language(request.getCustomerProfileDetails().getCustomerDetails().getLanguage())
                .citizenCode(request.getCustomerProfileDetails().getCustomerDetails().getCitizenCode())
                .residenceCode(request.getCustomerProfileDetails().getCustomerDetails().getResidenceCode())
                .withholdingCode(request.getCustomerProfileDetails().getCustomerDetails().getWithholdingCode())
                .withholdingPercent(request.getCustomerProfileDetails().getCustomerDetails().getWithholdingPercent())
                .passportId(request.getCustomerProfileDetails().getCustomerDetails().getPassportId())
                .countryOrigin(request.getCustomerProfileDetails().getCustomerDetails().getCountryOfBirth())
                .solicitableCode(request.getCustomerProfileDetails().getCustomerDetails().getSolicitableCode())
                .accomCode(request.getCustomerProfileDetails().getCustomerDetails().getAccommodationCode())
                .socioEconCode(request.getCustomerProfileDetails().getCustomerDetails().getSocioEconomicCode())
                .salutation(request.getCustomerProfileDetails().getCustomerDetails().getSalutation())
                .preferredCustCode(request.getCustomerProfileDetails().getCustomerDetails().getPreferredCustomerCode())
                .mktSegCode(request.getCustomerProfileDetails().getCustomerDetails().getMarketSegmentCode())
                .maritalStatusCode(request.getCustomerProfileDetails().getCustomerDetails().getMaritalStatusCode())
                .sendMailCode(request.getCustomerProfileDetails().getCustomerDetails().getSendMailCode())
                .moveInDt(request.getCustomerProfileDetails().getCustomerDetails().getMoveInDate())
                .deathDt(request.getCustomerProfileDetails().getCustomerDetails().getDateOfDeath())
                .custDocCode(request.getCustomerProfileDetails().getCustomerDetails().getCustomerDocumentCode())
                .custDocActivityDt(request.getCustomerProfileDetails()
                    .getCustomerDetails().getCustomerDocumentActivityDate())
                .custMailAcctCode(request.getCustomerProfileDetails().getCustomerDetails().getCustomerMailAccountCode())
                .custMailCustCode(request.getCustomerProfileDetails()
                    .getCustomerDetails().getCustomerMailCustomerCode())
                .custDlrDlrGroupFlag(
                    request.getCustomerProfileDetails().getCustomerDetails().getCustomerDealerGroupFlag())
                .custClassCode(request.getCustomerProfileDetails().getCustomerDetails().getCustomerClassCode())
                .custType(request.getCustomerProfileDetails().getCustomerDetails().getCustomerType())
                .custProspectCode(request.getCustomerProfileDetails().getCustomerDetails().getCustomerProspectCode())
                .customerCollectionsStatus(request.getCustomerProfileDetails()
                    .getCustomerDetails().getCustomerCollectionStatus())
                .cashExclusionCode(request.getCustomerProfileDetails().getCashExclusionCode())
                .cashExclusionLimit(getCashExclusionLimit(request))
                .custExtractCode(request.getCustomerProfileDetails().getCustomerExtractCode())
                .taxIdCertCode(request.getCustomerProfileDetails().getTaxIdCertificateCode())
                .tinActivityDt(request.getCustomerProfileDetails().getTinActivityDate())
                .taxIdTypeCode(request.getCustomerProfileDetails().getTaxIdTypeCode())
                .w8W9Num(request.getCustomerProfileDetails().getNumberOfW8OrW9())
                .udfCuusr3(request.getCustomerProfileDetails().getUserDefinedFlag3())
                .aliasName(getAliasName(request))
                .build())
            .build();

    }

    private static BirthDateType4 getBirthDt(final AddCustomerRequest request) {
        return BirthDateType4.builder()
            .date(request.getCustomerProfileDetails().getCustomerDetails().getBirthDetails().getBirthDate())
            .build();
    }

    private static CustProfBasicType getCustProfBasicType(final AddCustomerRequest request) {
        return CustProfBasicType.builder()
            .memoFlag(request.getCustomerProfileDetails().getMemoFlag())
            .rqMemoFlag(request.getCustomerProfileDetails().getRequestMemoFlag())
            .ticklerFlag(request.getCustomerProfileDetails().getTicklerFlag())
            .rqTicklerFlag(request.getCustomerProfileDetails().getRequestTicklerFlag())
            .moveInConfCode(request.getCustomerProfileDetails().getMoveInConferenceCode())
            .evePhoneAvailCode(request.getCustomerProfileDetails().getEveningPhoneAvailabilityCode())
            .dayPhoneAvailCode(request.getCustomerProfileDetails().getDayPhoneAvailabilityCode())
            .telexNum(request.getCustomerProfileDetails().getTicklerFlag())
            .telexAnswerBackNum(request.getCustomerProfileDetails().getTelexAnswerBackNumber())
            .activityLevelCode(request.getCustomerProfileDetails().getActivityLevelCode())
            .sourceCode(request.getCustomerProfileDetails().getSourceCode())
            .lifeStageCode(request.getCustomerProfileDetails().getLifeStageCode())
            .behavioralCode(request.getCustomerProfileDetails().getBehavioralCode())
            .responsivenessCode(request.getCustomerProfileDetails().getResponsivenessCode())
            .contactPrefText(request.getCustomerProfileDetails().getContactPreferenceText())
            .userKey(request.getCustomerProfileDetails().getUserKey())
            .custLinkage(request.getCustomerProfileDetails().getCustomerLinkage())
            .loyaltyCardNum(request.getCustomerProfileDetails().getLoyaltyCardNumber())
            .accessCode(request.getCustomerProfileDetails().getAccessCode())
            .cifmainBank(request.getCustomerProfileDetails().getOthers().getCiFMainBank())
            .countryOrigin(request.getCustomerProfileDetails().getOthers().getCountryOrigin())
            .cRBureau(request.getCustomerProfileDetails().getOthers().getCrBureau())
            .creditorCode(request.getCustomerProfileDetails().getOthers().getCreditorCode())
            .crossSell(request.getCustomerProfileDetails().getOthers().getCrossSell())
            .crossDef(request.getCustomerProfileDetails().getOthers().getCrossDef())
            .currYearDays(request.getCustomerProfileDetails().getOthers().getCurrentYearDays())
            .custAcctTyp(request.getCustomerProfileDetails().getOthers().getCustomerAccountType())
            .dateLastUpdate(request.getCustomerProfileDetails().getOthers().getLastUpdatedDate())
            .dateTagged(request.getCustomerProfileDetails().getOthers().getDateTagged())
            .fATCAStat(request.getCustomerProfileDetails().getOthers().getFaTCAStat())
            .holdMail1(request.getCustomerProfileDetails().getOthers().getHoldMail1())
            .holdMail2(request.getCustomerProfileDetails().getOthers().getHoldMail2())
            .lastYearDays(request.getCustomerProfileDetails().getOthers().getLastYearDays())
            .originatingBranch(request.getCustomerProfileDetails().getOthers().getOriginatingBranch())
            .percentage(request.getCustomerProfileDetails().getOthers().getPercentage())
            .powerAtty1(request.getCustomerProfileDetails().getOthers().getPowerOfAttorney1())
            .powerAtty2(request.getCustomerProfileDetails().getOthers().getPowerOfAttorney2())
            .refYear(request.getCustomerProfileDetails().getOthers().getReferenceYear())
            .remarkAction(request.getCustomerProfileDetails().getOthers().getRemarkAction())
            .riskCode(request.getCustomerProfileDetails().getOthers().getRiskCode())
            .standInstr1(request.getCustomerProfileDetails().getOthers().getStandInstruction1())
            .standInstr2(request.getCustomerProfileDetails().getOthers().getStandInstruction2())
            .substPres(request.getCustomerProfileDetails().getOthers().getSubstantialPresenc())
            .substOwner(request.getCustomerProfileDetails().getOthers().getSubstantialOwner())
            .twoYearsAgo(request.getCustomerProfileDetails().getOthers().getTwoYearsAgo())
            .twoYearAgoDays(request.getCustomerProfileDetails().getOthers().getTwoYearAgoDays())
            .uDFCuusr1(request.getCustomerProfileDetails().getOthers().getUDFCuusr1())
            .uSTIN(request.getCustomerProfileDetails().getOthers().getUSTIN())
            .usaddr1(request.getCustomerProfileDetails().getOthers().getUsaddr1())
            .usaddr2(request.getCustomerProfileDetails().getOthers().getUsaddr2())
            .usaddr3(request.getCustomerProfileDetails().getOthers().getUsaddr3())
            .usaddr4(request.getCustomerProfileDetails().getOthers().getUsaddr4())
            .userFld1(request.getCustomerProfileDetails().getOthers().getUserFld1())
            .userFld2(request.getCustomerProfileDetails().getOthers().getUserFld2())
            .userFld3(request.getCustomerProfileDetails().getOthers().getUserFld3())
            .userFld4(request.getCustomerProfileDetails().getOthers().getUserFld4())
            .userFld5(request.getCustomerProfileDetails().getOthers().getUserFld5())
            .userFld6(request.getCustomerProfileDetails().getOthers().getUserFld6())
            .userFld7(request.getCustomerProfileDetails().getOthers().getUserFld7())
            .userFld8(request.getCustomerProfileDetails().getOthers().getUserFld8())
            .userFld9(request.getCustomerProfileDetails().getOthers().getUserFld9())
            .userFld10(request.getCustomerProfileDetails().getOthers().getUserFld10())
            .uSPOBox(request.getCustomerProfileDetails().getOthers().getUSPOBox())
            .uSTelNbr(request.getCustomerProfileDetails().getOthers().getUSTelNbr())
            .uSZipCode(request.getCustomerProfileDetails().getOthers().getUSZipCode())
            .taxInfoCode(null)
            .build();
    }

    private static SSNIDType getTaxId(final AddCustomerRequest request) {
        return SSNIDType.builder()
            .id(request.getCustomerProfileDetails().getTaxDetails().getTaxId())
            .build();
    }

    private static CustProfBasicTypeCashExclusionLimit getCashExclusionLimit(final AddCustomerRequest request) {
        return CustProfBasicTypeCashExclusionLimit.builder()
            .amt(request.getCustomerProfileDetails().getCashExclusionLimit().getAmount())
            .build();
    }

    private static CustProfBasicTypeEmpIncomeAmt getEmpIncomeAmt(final AddCustomerRequest request) {
        return CustProfBasicTypeEmpIncomeAmt.builder()
            .amt(request.getCustomerProfileDetails().getEmploymentDetails()
                .getEmployeeIncomeDetails().getEmployeeIncomeAmount())
            .build();
    }

    private static PersonFullNameType getPersonName(final AddCustomerRequest request) {
        return PersonFullNameType.builder()
            .lastName(request.getCustomerProfileDetails().getPersonDetails().getLastName())
            .firstName(request.getCustomerProfileDetails().getPersonDetails().getFirstName())
            .middleName(request.getCustomerProfileDetails().getPersonDetails().getMiddleName())
            .titlePrefix(request.getCustomerProfileDetails().getPersonDetails().getTitlePrefix())
            .businessName(request.getCustomerProfileDetails().getPersonDetails().getBusinessName())
            .nameSuffix(request.getCustomerProfileDetails().getPersonDetails().getNameSuffix())
            .build();
    }

    private static PersonFullNameType getAliasName(final AddCustomerRequest request) {
        return PersonFullNameType.builder()
            .lastName(request.getCustomerProfileDetails().getAliasDetails().getLastName())
            .secondSurname(request.getCustomerProfileDetails().getAliasDetails().getSecondSurname())
            .firstName(request.getCustomerProfileDetails().getAliasDetails().getFirstName())
            .middleName(request.getCustomerProfileDetails().getAliasDetails().getMiddleName())
            .titlePrefix(request.getCustomerProfileDetails().getAliasDetails().getTitlePrefix())
            .nameSuffix(request.getCustomerProfileDetails().getAliasDetails().getNameSuffix())
            .businessName(request.getCustomerProfileDetails().getAliasDetails().getBusinessName())
            .build();
    }

    private static List<CardAcctIdTypeProf> getCardAcctIds(final AddCustomerRequest request) {
        return List.of(CardAcctIdTypeProf.builder()
            .acctId(request.getCustomerProfileDetails().getCardAccountDetails().getAccountId())
            .acctType(request.getCustomerProfileDetails().getCardAccountDetails().getAccountType())
            .cardHolderFlag(request.getCustomerProfileDetails().getCardAccountDetails().getCardHolderFlag())
            .ccMotoAcctType(CCMotoAcctType.builder()
                .expDt(request.getCustomerProfileDetails().getMotoAccountType().getExpiryDate())
                .build())
            .build());
    }

    private static List<PhoneNumTypeWithExtension> getPhoneNa(final AddCustomerRequest request) {
        return List.of(PhoneNumTypeWithExtension.builder()
            .intlDialCode(request.getCustomerProfileDetails().getPhoneNumberDetails()
                .get(EvolutionConstantUtils.ZERO).getInitialDialCode())
            .phoneType(request.getCustomerProfileDetails().getPhoneNumberDetails()
                .get(EvolutionConstantUtils.ZERO).getPhoneType())
            .phone(request.getCustomerProfileDetails().getPhoneNumberDetails()
                .get(EvolutionConstantUtils.ZERO).getPhone())
            .phoneExt(request.getCustomerProfileDetails().getPhoneNumberDetails()
                .get(EvolutionConstantUtils.ZERO).getExtension())
            .build());
    }

    private static CustBasicAddrType getBuild(final AddCustomerRequest request) {
        return CustBasicAddrType.builder()
            .fullName(request.getCustomerProfileDetails().getCustomerAddressDetail().getFullName())
            .addr1(request.getCustomerProfileDetails().getCustomerAddressDetail().getLine1())
            .addr2(request.getCustomerProfileDetails().getCustomerAddressDetail().getLine2())
            .addr3(request.getCustomerProfileDetails().getCustomerAddressDetail().getLine3())
            .addr4(request.getCustomerProfileDetails().getCustomerAddressDetail().getLine4())
            .stateProv(request.getCustomerProfileDetails().getCustomerAddressDetail().getProvince())
            .postLocaleCode(request.getCustomerProfileDetails().getCustomerAddressDetail().getPostalCode())
            .country(request.getCustomerProfileDetails().getCustomerAddressDetail().getCountry())
            .build();
    }

    private AddCustomerResponse mapToAddCustomerResponse(
        final CustProfBasicAddRs response,
        final ServletUriComponentsBuilder servletUriComponentsBuilder) {

        response.setRqUID(Optional.ofNullable(response.getRqUID())
                .orElseGet(RqUID::new));
        response.setCardAcctId(Optional.ofNullable(response.getCardAcctId())
                .orElseGet(CardAcctIdType::new));
        response.setCustId(Optional.ofNullable(response.getCustId())
                .orElseGet(CustIdType::new));

        // Create and populate the CustomerInquireResponseData object
        final AddCustomerResponseData customerResponseData = AddCustomerResponseData.builder()
            .requestId(response.getRqUID().getRequestUid())
            .cardAccountDetails(
                AddCustomerResponseDataCardAccountDetails.builder()
                    .accountId(response.getCardAcctId().getAcctId())
                    .accountType(response.getCardAcctId().getAcctType())
                    .build())
            .customerId(
                AddCustomerResponseDataCustomerId.builder()
                    .customerNumber(response.getCustId().getCustPermId())
                    .build())
            .build();
        // Create and populate the CustomerInquireResponse object
        return AddCustomerResponse.builder()
            .data(customerResponseData)
            .meta(Meta.builder()
                .totalPages(EvolutionConstantUtils.ONE)
                .firstAvailableDateTime(EvolutionConstantUtils.EMPTY_STRING)
                .lastAvailableDateTime(EvolutionConstantUtils.EMPTY_STRING)
                .build())
            .links(Links.builder()
                .first(EvolutionConstantUtils.EMPTY_STRING)
                .last(EvolutionConstantUtils.EMPTY_STRING)
                .next(EvolutionConstantUtils.EMPTY_STRING)
                .prev(EvolutionConstantUtils.EMPTY_STRING)
                .self(servletUriComponentsBuilder.build().toString())
                .build())
            .build();
    }
}

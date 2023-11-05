package com.bdo.evolution_native.service.impl;

import com.bdo.evolution_native.client.CustomerListClient;
import com.bdo.evolution_native.client.model.customerlist.*;
import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.bdo.evolution_native.exception.UseCaseNotValidException;
import com.bdo.evolution_native.model.Links;
import com.bdo.evolution_native.model.Meta;
import com.bdo.evolution_native.model.customerlist.*;
import com.bdo.evolution_native.service.CustomerAccountSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * The type Customer account search service.
 */
@Service
public class CustomerAccountSearchServiceImpl implements CustomerAccountSearchService {
    @Autowired
    private CustomerListClient customerListClient;

    /**
     * Customer Account Search Service
     */
    @Override
    public Mono<CustomerSearchInquiryResponse> retriveCustomer(
        final CustomerSearchInquiryRequest customerSearchInquiryRequest,
        final ServletUriComponentsBuilder servletUriComponentsBuilder) {
        if (Objects.isNull(customerSearchInquiryRequest)) {
            throw new UseCaseNotValidException(EvolutionConstantUtils.USECASE_NOT_VALID, new Throwable());
        }
        final Mono<CustListInqRs> custListInqRsMono = determineSorResponse(customerSearchInquiryRequest);
        return custListInqRsMono
            .map(custListInqRs -> CustomerSearchInquiryResponse.builder()
                .data(convertSorPayloadToMsDataPayload(custListInqRs))
                .meta(Meta.builder()
                    .totalPages(EvolutionConstantUtils.ONE)
                    .firstAvailableDateTime(EvolutionConstantUtils.EMPTY_STRING)
                    .lastAvailableDateTime(EvolutionConstantUtils.EMPTY_STRING)
                    .build()).links(Links.builder()
                    .first(EvolutionConstantUtils.EMPTY_STRING)
                    .last(EvolutionConstantUtils.EMPTY_STRING)
                    .next(EvolutionConstantUtils.EMPTY_STRING)
                    .prev(EvolutionConstantUtils.EMPTY_STRING)
                    .self(servletUriComponentsBuilder.build().toString())
                    .build()).build()
            );
    }

    /**
     * Customer Account sor conersion method
     */

    private CustomerSearchInquiryResponsePayload convertSorPayloadToMsDataPayload(final CustListInqRs sorResponse) {
        final List<CustBasicDtlType> customerBasics = sorResponse.getCustBasics();
        final CustBasicDtlType customerBasic = Objects.nonNull(customerBasics)
            ? customerBasics.get(EvolutionConstantUtils.ZERO) : CustBasicDtlType.builder()
            .build();

        return CustomerSearchInquiryResponsePayload.builder()
            .reference(Reference.builder()
                .customerPermanentId(
                    Optional.ofNullable(customerBasic.getCustId()).map(
                        CustIdType::getCustPermId).orElse(EvolutionConstantUtils.EMPTY_STRING))
                .customerType(
                    Optional.ofNullable(customerBasic.getCustType())
                        .orElse(EvolutionConstantUtils.EMPTY_STRING))
                .customerMailCustomerCode(
                    Optional.ofNullable(customerBasic.getCustMailCustCode())
                        .orElse(EvolutionConstantUtils.EMPTY_STRING))
                .customerStatusCode(
                    Optional.ofNullable(customerBasic.getCustStatusCode())
                        .orElse(EvolutionConstantUtils.EMPTY_STRING))
                .custAccountRelationshipType(
                    Optional.ofNullable(customerBasic.getCustAcctRelType())
                        .orElse(EvolutionConstantUtils.EMPTY_STRING))
                .customerName(
                    Optional.ofNullable(customerBasic.getCustAddr()).map(
                        NonParsedAddrType::getFullName).orElse(EvolutionConstantUtils.EMPTY_STRING))
                .dateOfBirth(
                    Optional.ofNullable(customerBasic.getBirthDt()).map(
                        BirthDateType::getDate).orElse(EvolutionConstantUtils.EMPTY_STRING))
                .citizenCode(Optional.ofNullable(customerBasic.getCitizenCode())
                    .orElse(EvolutionConstantUtils.EMPTY_STRING))
                .residenceCode(Optional.ofNullable(customerBasic.getResidenceCode())
                    .orElse(EvolutionConstantUtils.EMPTY_STRING))
                .riskCode(Optional.ofNullable(customerBasic.getRiskCode())
                    .orElse(EvolutionConstantUtils.EMPTY_STRING))
                .customerAddress(CustomerAddress.builder()
                    .addressCode(Optional.ofNullable(customerBasic.getCustAddr()).map(
                        NonParsedAddrType::getAddrCode).orElse(EvolutionConstantUtils.EMPTY_STRING))
                    .houseNumber(Optional.ofNullable(customerBasic.getCustAddr()).map(
                        NonParsedAddrType::getHouseNum).orElse(EvolutionConstantUtils.EMPTY_STRING))
                    .street(Optional.ofNullable(customerBasic.getCustAddr()).map(
                        NonParsedAddrType::getStreet).orElse(EvolutionConstantUtils.EMPTY_STRING))
                    .district(Optional.ofNullable(customerBasic.getCustAddr()).map(
                        NonParsedAddrType::getDistrict).orElse(EvolutionConstantUtils.EMPTY_STRING))
                    .city(Optional.ofNullable(customerBasic.getCustAddr()).map(
                        NonParsedAddrType::getCity).orElse(EvolutionConstantUtils.EMPTY_STRING))
                    .state(Optional.ofNullable(customerBasic.getCustAddr()).map(
                        NonParsedAddrType::getStateProv).orElse(EvolutionConstantUtils.EMPTY_STRING))
                    .country(Optional.ofNullable(customerBasic.getCustAddr()).map(
                        NonParsedAddrType::getCountry).orElse(EvolutionConstantUtils.EMPTY_STRING))
                    .zipCode(Optional.ofNullable(customerBasic.getCustAddr()).map(
                        NonParsedAddrType::getZipCode).orElse(EvolutionConstantUtils.EMPTY_STRING))
                    .build())
                .nationalId(
                    Optional.ofNullable(customerBasic.getNationalId()).map(
                        NINIDType::getId).orElse(EvolutionConstantUtils.EMPTY_STRING))
                .taxInformationCode(
                    Optional.ofNullable(customerBasic.getTaxInfoCode()).map(
                        TINIDType::getId).orElse(EvolutionConstantUtils.EMPTY_STRING))
                .taxId(Optional.ofNullable(customerBasic.getTaxId()).map(
                    SSNIDType::getId).orElse(EvolutionConstantUtils.EMPTY_STRING))
                .branchId(Optional.ofNullable(customerBasic.getBranchId())
                    .orElse(EvolutionConstantUtils.EMPTY_STRING))
                .build())
            .build();

    }

    /**
     * Customer Account Search sor response condition check method
     */

    private Mono<CustListInqRs> determineSorResponse(final CustomerSearchInquiryRequest request) {

        final CustListInqRq custListInqRq = new CustListInqRq();
        final Map<Predicate<CustomerSearchInquiryRequest>,
            Consumer<CustomerSearchInquiryRequest>> conditions = getPredicateConsumerMap(custListInqRq);

        if (conditions.entrySet().stream()
            .filter(entry -> entry.getKey().test(request)).findFirst().isEmpty()) {
            throw new UseCaseNotValidException(EvolutionConstantUtils.USECASE_NOT_VALID, new Throwable());
        }

        conditions.entrySet().stream()
            .filter(entry -> entry.getKey().test(request))
            .forEach(element -> {
                element.getValue().accept(request);
                custListInqRq.setSearchType(request.getSearchType());
            });
        return customerListClient.customerClient(custListInqRq);
    }

    private static Map<Predicate<CustomerSearchInquiryRequest>,
        Consumer<CustomerSearchInquiryRequest>> getPredicateConsumerMap(final CustListInqRq custListInqRq) {
        final Map<Predicate<CustomerSearchInquiryRequest>,
            Consumer<CustomerSearchInquiryRequest>> conditions = new ConcurrentHashMap<>();
        conditions.put(
            req -> Objects.nonNull(req.getDebitCardNumber()),
            req -> {
                final String modifiedDebitCard = req.getDebitCardNumber().substring(0, 6)
                    + "000" + req.getDebitCardNumber().substring(6) + "1";
                custListInqRq.setDebitCardNumber(modifiedDebitCard);
            }
        );
        conditions.put(
            req -> Objects.nonNull(req.getAcctNumber()) && Objects.nonNull(req.getAcctType()),
            req -> {
                final BankAcctIdType bankAcctIdType = new BankAcctIdType();
                bankAcctIdType.setAcctId(req.getAcctNumber());
                bankAcctIdType.setAcctType(req.getAcctType());
                custListInqRq.setBankAcctId(bankAcctIdType);
            }
        );
        conditions.put(
            req -> Objects.nonNull(req.getCif()),
            req -> custListInqRq.setCustId(req.getCif())
        );
        conditions.put(
            req -> Objects.nonNull(req.getMobileNumber()),
            req -> custListInqRq.setMobileNum(req.getMobileNumber())
        );
        conditions.put(
            req -> Objects.nonNull(req.getEMailAddress()),
            req -> custListInqRq.setEMailAddress(req.getEMailAddress())
        );
        conditions.put(
            req -> Objects.nonNull(req.getTaxId()),
            req -> custListInqRq.setTaxId(req.getTaxId())
        );
        conditions.put(
            req -> Objects.nonNull(req.getFirstName())
                && Objects.nonNull(req.getLastName())
                && Objects.nonNull(req.getBirthDt()),
            req -> {
                custListInqRq.setFirstName(req.getFirstName());
                custListInqRq.setSecondName(req.getSecondName());
                custListInqRq.setLastName(req.getLastName());
                custListInqRq.setCustomerType(req.getCustomerType());
                custListInqRq.setTaxId(req.getTaxId());
            }
        );
        return conditions;
    }
}

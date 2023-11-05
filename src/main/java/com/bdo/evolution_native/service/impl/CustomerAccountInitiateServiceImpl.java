package com.bdo.evolution_native.service.impl;

import com.bdo.evolution_native.client.CustomerAccountInitiateClient;
import com.bdo.evolution_native.client.model.RqUID;
import com.bdo.evolution_native.client.model.custacctadd.*;
import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.bdo.evolution_native.model.Links;
import com.bdo.evolution_native.model.Meta;
import com.bdo.evolution_native.model.custacctadd.*;
import com.bdo.evolution_native.service.CustomerAccountInitiateService;
import com.bdo.evolution_native.util.MethodLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

/**
 * The type Customer Account details initiate service.
 */
@Service
public class CustomerAccountInitiateServiceImpl implements CustomerAccountInitiateService {
    @Autowired
    private CustomerAccountInitiateClient customerAccountInitiateClient;

    @MethodLogger
    @Override
    public Mono<AccountResponse> initiateCustomerAccount(
        final AccountRequest accountRequest,
        final ServletUriComponentsBuilder servletUriComponentsBuilder) {
        final AcctCustAddRq acctCustAddRq = mapToAcctCustAddRq(accountRequest);
        final Mono<AcctCustAddRs> acctCustAddRs = customerAccountInitiateClient.customerClient(acctCustAddRq);
        return acctCustAddRs.map(response -> mapToAccountResponse(response, servletUriComponentsBuilder));
    }

    @MethodLogger
    private AcctCustAddRq mapToAcctCustAddRq(final AccountRequest request) {
        setDefaultValuesIfNull(request);
        return AcctCustAddRq.builder()
            .rqUID(RqUID.builder()
                .requestUid(request.getSavingAccountFacility().getCustomerReference().getRequestId())
                .build())
            .aliasBankAcctId(AliasBankAcctIdType.builder()
                .aliasAcctType(request.getSavingAccountFacility().getAccountDetails()
                    .getAliasBankAccountId().getAccountType())
                .aliasAcctId(request.getSavingAccountFacility().getAccountDetails()
                    .getAliasBankAccountDetails().getAliasAccountId())
                .aliasAcctIdMasked(request.getSavingAccountFacility().getAccountDetails()
                    .getAliasBankAccountDetails().getAliasAccountIdMasked())
                .build())
            .bankAcctId(BankAcctIdType.builder()
                .acctType(request.getSavingAccountFacility().getAccountDetails()
                    .getBankAccountDetails().getAccountType())
                .acctId(request.getSavingAccountFacility().getAccountDetails()
                    .getBankAccountDetails().getAccountId())
                .acctIdMasked(request.getSavingAccountFacility().getAccountDetails()
                    .getBankAccountDetails().getAccountIdMasked())
                .build())
            .acctRelations(List.of(AcctRelationTypeTaxLiability.builder()
                .custId(CustIdType.builder()
                    .custPermId(request.getSavingAccountFacility().getAccountRelations()
                        .get(0).getCustomerId().getCustomerNumber())
                    .custPermIdMasked(request.getSavingAccountFacility().getAccountRelations()
                        .get(0).getCustomerId().getCustomerNumberMasked())
                    .build())
                .relationCode(request.getSavingAccountFacility().getAccountRelations()
                    .get(0).getRelationCode())
                .taxLiabilityPctAmt(request.getSavingAccountFacility().getTaxReference()
                    .getTaxLiabilityPercentageAmount())
                .noticeFlagDescription(request.getSavingAccountFacility().getTaxReference()
                    .getNoticeFlagDescription())
                .build()))
            .build();
    }

    @SuppressWarnings("PMD")
    @MethodLogger
    private AccountResponse mapToAccountResponse(
        final AcctCustAddRs response,
        final ServletUriComponentsBuilder servletUriComponentsBuilder) {

        final AccountResponsePayload accountResponsePayload = AccountResponsePayload.builder()
            .savingAccountFacility(SavingAccountFacilityResponse.builder().build())
            .build();

        return AccountResponse.builder()
            .data(accountResponsePayload)
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

    @MethodLogger
    private void setDefaultValuesIfNull(final AccountRequest accountRequest) {
        accountRequest.getSavingAccountFacility()
            .setCustomerReference(Optional.ofNullable(accountRequest.getSavingAccountFacility()
                .getCustomerReference()).orElseGet(CustomerReference::new));
        accountRequest.getSavingAccountFacility()
            .getAccountDetails().setAliasBankAccountId(Optional.ofNullable(accountRequest.getSavingAccountFacility()
                .getAccountDetails().getAliasBankAccountId()).orElseGet(AliasBankAcctId::new));
        accountRequest.getSavingAccountFacility()
            .getAccountDetails().setAliasBankAccountDetails(Optional.ofNullable(accountRequest
                .getSavingAccountFacility().getAccountDetails()
                .getAliasBankAccountDetails()).orElseGet(AliasBankAccountDetails::new));
        accountRequest.getSavingAccountFacility()
            .setTaxReference(Optional.ofNullable(accountRequest.getSavingAccountFacility()
                .getTaxReference()).orElseGet(TaxReference::new));
    }
}

package com.bdo.evolution_native.service.impl;

import com.bdo.evolution_native.client.AddCardInfoClient;
import com.bdo.evolution_native.client.model.addcard.*;
import com.bdo.evolution_native.client.model.initiatemodel.RqUID;
import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.bdo.evolution_native.model.Links;
import com.bdo.evolution_native.model.Meta;
import com.bdo.evolution_native.model.addcard.*;
import com.bdo.evolution_native.service.AddCardInfoService;
import com.bdo.evolution_native.util.MethodLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

/**
 * The type AddCard service.
 */

@Service
public class AddCardInfoServiceImpl implements AddCardInfoService {
    @Autowired
    private AddCardInfoClient addCardInfoClient;

    @MethodLogger
    @Override
    public Mono<CardResponse> addCardInfoPost(final CardRequest request, final ServletUriComponentsBuilder servlet) {
        final Mono<CardAcctAddRs> cardAcctAddRs = addCardInfoClient.addCardInfoClient(
            mapToCardAcctAddRq(request));
        return cardAcctAddRs.map(response -> mapToCardResponse(response, servlet));
    }

    @MethodLogger
    private CardResponse mapToCardResponse(final CardAcctAddRs response, final ServletUriComponentsBuilder servlet) {
        setAddCardValueIfNull(response);
        return CardResponse.builder()
            .data(CardResponsePayload.builder()
                .customerReference(CustomerReferenceResponse.builder()
                    .requestId(response.getRqUID().getRequestUid())
                    .customerId(CustomerIdResponse.builder()
                        .customerNumber(response.getCustId().getCustPermId())
                        .customerNumberMasked(response.getCustId().getCustPermIdMasked())
                        .build())
                    .shortName(response.getCardAcctRec().getShortName())
                    .accountDetails(AccountDetailsResponse.builder()
                        .cardAccountDetails(CardAccountDetailsResponse.builder()
                            .cardAccountId(response.getCardAcctRec().getCardAcctInfo().getCardAcctId().getAcctId())
                            .accountNumberMasked(response.getCardAcctRec().getCardAcctInfo()
                                .getCardAcctId().getAcctIdMasked())
                            .accountType(response.getCardAcctRec().getCardAcctInfo().getCardAcctId().getAcctType())
                            .bankId(response.getCardAcctRec().getCardAcctInfo().getBankId())
                            .isOId(response.getCardAcctRec().getCardAcctInfo().getIsoId())
                            .memberId(response.getCardAcctRec().getCardAcctInfo().getMemberId())
                            .cardTypeCode(response.getCardAcctRec().getCardAcctInfo().getCardTypeCode())
                            .build())
                        .build())
                    .build())
                .cardFacility(CardFacilityResponse.builder().build())
                .build())
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
                .self(servlet.build().toString())
                .build())
            .build();
    }

    @MethodLogger
    private CardAcctAddRq mapToCardAcctAddRq(final CardRequest request) {
        setDefaultValuesIfNull(request);
        return CardAcctAddRq.builder()
            .rqUID(RqUID.builder()
                .requestUid(request.getCardFacility().getCustomerReference().getRequestId())
                .build())
            .cardAcctDtlInfo(CardAcctDtlInfoAddType.builder()
                .custId(CustIdType.builder()
                    .custPermId(request.getCardFacility().getCustomerReference().getCustomerId().getCustomerNumber())
                    .custPermIdMasked(request.getCardFacility().getCustomerReference()
                        .getCustomerId().getCustomerNumberMasked())
                    .build())
                .langCode(request.getCardFacility().getCustomerReference().getLanguageCode())
                .cardAcctId(CardAcctIdType.builder()
                    .acctId(request.getCardFacility().getAccountDetailsRequest().getCardAccountdetails().getAccountId())
                    .acctIdMasked(request.getCardFacility().getAccountDetailsRequest().getCardAccountdetails()
                        .getAccountIdMasked())
                    .acctType(request.getCardFacility().getAccountDetailsRequest().getCardAccountdetails()
                        .getAccountType())
                    .build())
                .shortName(request.getCardFacility().getAccountDetailsRequest().getCardAccountdetails().getShortName())
                .isoId(request.getCardFacility().getAccountDetailsRequest().getCardAccountdetails().getIsOId())
                .vipClass(request.getCardFacility().getAccountDetailsRequest().getCardAccountdetails().getVipClass())
                .wdBalOverrideFlag(request.getCardFacility().getAccountDetailsRequest()
                    .getWithdrawlBalanceOverrideFlag())
                .cardGenNum(request.getCardFacility().getAccountDetailsRequest().getCardGenerateNumber())
                .cardGenNxtCycleFlag(request.getCardFacility().getAccountDetailsRequest()
                    .getCardGenerateNextCycleFlag())
                .pinMailerNxtCycleCode(request.getCardFacility().getAccountDetailsRequest().getPinMailerNextCycle())
                .waiveTrnFeeFlag(request.getCardFacility().getAccountDetailsRequest().getWaiveCardFeeFlag())
                .waiveCardFeeFlag(request.getCardFacility().getAccountDetailsRequest().getWaiveCardFeeFlag())
                .thirdPartyXferFlag(request.getCardFacility().getAccountDetailsRequest().getThirdPartyTransferFlag())
                .nextCardReissueDt(request.getCardFacility().getAccountDetailsRequest().getNextCardReissueDate())
                .nextPINIssueDt(request.getCardFacility().getAccountDetailsRequest().getNextPinIssueDate())
                .nextCardFeeDt(request.getCardFacility().getAccountDetailsRequest().getNextCardFeeDate())
                .embossingName(request.getCardFacility().getAccountDetailsRequest().getEmbossingName())
                .remoteBankingFlag(request.getCardFacility().getAccountDetailsRequest().getRemoteBankingFlag())
                .cardTypeCode(request.getCardFacility().getAccountDetailsRequest().getCardTypeCode())
                .cardStatusCode(request.getCardFacility().getAccountDetailsRequest().getCardStatusCode())
                .cardExpDt(request.getCardFacility().getAccountDetailsRequest().getCardExpiryDate())
                .expirationSpecDay(request.getCardFacility().getAccountDetailsRequest().getExpirationSpecificDay())
                .build())
            .acctLinkCnt(request.getCardFacility().getAccountDetailsRequest().getCardAccountdetails()
                .getAccountLinkCount())
            .oldCardNum(request.getCardFacility().getAccountDetailsRequest().getOldCardNumberType())
            .branchCode(request.getCardFacility().getBankBranchLocationReference().getBranchCode())
            .branchFlag(request.getCardFacility().getBankBranchLocationReference().getBranchFlagType())
            .acctLinks(List.of(AcctLinkType.builder()
                .bankAcctId(BankAcctIdType.builder()
                    .acctId(request.getCardFacility().getLinkedAccounts().getAccountLinks()
                        .get(EvolutionConstantUtils.ZERO).getBankAccountIdType().getAccountId())
                    .acctIdMasked(request.getCardFacility().getLinkedAccounts().getAccountLinks()
                        .get(EvolutionConstantUtils.ZERO).getBankAccountIdType().getAccountIdMasked())
                    .acctType(request.getCardFacility().getLinkedAccounts().getAccountLinks()
                        .get(EvolutionConstantUtils.ZERO).getBankAccountIdType().getAccountType())
                    .build())
                .seqId(request.getCardFacility().getLinkedAccounts().getAccountLinks()
                    .get(EvolutionConstantUtils.ZERO).getSequenceId())
                .build()))
            .build();
    }

    @MethodLogger
    private void setDefaultValuesIfNull(final CardRequest cardRequest) {
        cardRequest.getCardFacility()
            .setLinkedAccounts(Optional.ofNullable(cardRequest.getCardFacility()
                .getLinkedAccounts()).orElseGet(LinkedAccounts::new));
        cardRequest.getCardFacility()
            .getLinkedAccounts()
            .setAccountLinks(Optional.ofNullable(cardRequest.getCardFacility()
                    .getLinkedAccounts().getAccountLinks())
                .orElse(List.of(AccountLinks.builder().build())));
        cardRequest.getCardFacility().getLinkedAccounts().getAccountLinks().get(EvolutionConstantUtils.ZERO)
            .setBankAccountIdType(Optional.ofNullable(cardRequest.getCardFacility()
                .getLinkedAccounts().getAccountLinks().get(EvolutionConstantUtils.ZERO)
                .getBankAccountIdType()).orElseGet(BankAccountIdType::new));
        cardRequest.getCardFacility()
            .setBankBranchLocationReference(Optional.ofNullable(cardRequest.getCardFacility()
                .getBankBranchLocationReference()).orElseGet(BankBranchLocationReference::new));

    }

    @MethodLogger
    private void setAddCardValueIfNull(final CardAcctAddRs cardAcctAddRs) {
        cardAcctAddRs.setRqUID(Optional.ofNullable(cardAcctAddRs.getRqUID()).orElseGet(RqUID::new));
    }
}

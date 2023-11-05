package com.bdo.evolution_native.service;

import com.bdo.evolution_native.model.CurrentBalanceInquiryResponse;
import com.bdo.evolution_native.model.SavingBalanceInquiryResponse;
import com.bdo.evolution_native.model.TimeDepositBalanceInquiryResponse;
import com.bdo.evolution_native.model.loan.model.LoanBalanceInquiryResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

/**
 * This class is  for Balance Inquiry Service
 */
public interface BalanceInquiryService {

    Mono<CurrentBalanceInquiryResponse> getCurrentAccountBalance(String currentAccountId,
                                                                 ServletUriComponentsBuilder servlet);

    Mono<SavingBalanceInquiryResponse> getSavingsAccountBalance(String savingsAccountId,
                                                                ServletUriComponentsBuilder servlet);

    Mono<TimeDepositBalanceInquiryResponse> getTimeDepositAccountBalance(String timeDepositId,
                                                                         ServletUriComponentsBuilder servlet);

    Mono<LoanBalanceInquiryResponse> getLoanAccountBalance(String loanAccountId,
                                                           ServletUriComponentsBuilder servlet);
}

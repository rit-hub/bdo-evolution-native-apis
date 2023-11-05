package com.bdo.evolution_native.service.impl;

import com.bdo.evolution_native.client.BalanceInquiryClient;
import com.bdo.evolution_native.client.model.BalanceInquiryRequest;
import com.bdo.evolution_native.client.model.BalanceInquiryResponse;
import com.bdo.evolution_native.client.model.DepartmentAccountId;
import com.bdo.evolution_native.client.model.LoanAccountId;
import com.bdo.evolution_native.client.model.LoanBalanceInquiryRequestSor;
import com.bdo.evolution_native.client.model.LoanBalanceInquiryResponseSor;
import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.bdo.evolution_native.enums.AccountType;
import com.bdo.evolution_native.model.Account;
import com.bdo.evolution_native.model.AccountBalance;
import com.bdo.evolution_native.model.AccountBalances;
import com.bdo.evolution_native.model.AccountFacility;
import com.bdo.evolution_native.model.AccountIdentification;
import com.bdo.evolution_native.model.AccountTypes;
import com.bdo.evolution_native.model.CurrencyCode;
import com.bdo.evolution_native.model.CurrentAccountFacility;
import com.bdo.evolution_native.model.CurrentAccountFacilityResponse;
import com.bdo.evolution_native.model.CurrentBalanceInquiryResponse;
import com.bdo.evolution_native.model.FieldValue;
import com.bdo.evolution_native.model.Identifier;
import com.bdo.evolution_native.model.Links;
import com.bdo.evolution_native.model.Meta;
import com.bdo.evolution_native.model.SavingAccountFacility;
import com.bdo.evolution_native.model.SavingAccountFacilityResponse;
import com.bdo.evolution_native.model.SavingBalanceInquiryResponse;
import com.bdo.evolution_native.model.TimeDepositAccountFacility;
import com.bdo.evolution_native.model.TimeDepositAccountFacilityResponse;
import com.bdo.evolution_native.model.TimeDepositBalanceInquiryResponse;
import com.bdo.evolution_native.model.loan.model.Accountbalances;
import com.bdo.evolution_native.model.loan.model.Amount;
import com.bdo.evolution_native.model.loan.model.Currencycode;
import com.bdo.evolution_native.model.loan.model.LoanBalanceInquiryResponse;
import com.bdo.evolution_native.model.loan.model.RetrieveLoanFacilityResponse;
import com.bdo.evolution_native.model.loan.model.RetrieveLoanFacilityResponseLoanFacility;
import com.bdo.evolution_native.service.BalanceInquiryService;
import com.bdo.evolution_native.util.MethodLogger;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is Balance Inquiry ServiceImpl for different type of account
 */
@Service
public class BalanceInquiryServiceImpl implements BalanceInquiryService {

    @Autowired
    private BalanceInquiryClient balanceInquiryClient;

    /**
     * This method returning getting Sor Response and getting Current Account Balance Response
     *
     * @param currentAccountId
     * @param servletUriComponentsBuilder
     * @return CurrentAccountResponse
     */
    @Override
    @MethodLogger
    public Mono<CurrentBalanceInquiryResponse> getCurrentAccountBalance(
            final String currentAccountId, final ServletUriComponentsBuilder servletUriComponentsBuilder) {
        final Mono<BalanceInquiryResponse> sorResponse = getSorResponse(currentAccountId, AccountType.DD.toCode());
        return sorResponse.map(response -> {
            final CurrentAccountFacility currentAccountFacility = new CurrentAccountFacility();
            currentAccountFacility.setAccountFacility(getAccountFacility(response));
            final Identifier identifier = new Identifier();
            identifier.setIdentifierFieldValue(new FieldValue(response.getDepAcctId().getAcctId()));
            final AccountIdentification accountIdentification = new AccountIdentification();
            accountIdentification.setIdentifier(identifier);
            //setting account number
            currentAccountFacility.setCurrentAccountNumber(accountIdentification);
            final CurrentAccountFacilityResponse currentAccountFacilityResponse = new
                    CurrentAccountFacilityResponse(currentAccountFacility);
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));

            return objectMapper.convertValue(new CurrentBalanceInquiryResponse(currentAccountFacilityResponse,
                    getLinks(servletUriComponentsBuilder), getMeta()), CurrentBalanceInquiryResponse.class);
        });
    }

    /**
     * This method returning getting Sor Response and getting Savings Account Balance Response
     *
     * @param savingsAccountId
     * @param servletUriComponentsBuilder
     * @return SavingBalanceInquiryResponse
     */
    @Override
    @SneakyThrows
    @MethodLogger
    public Mono<SavingBalanceInquiryResponse> getSavingsAccountBalance(
            final String savingsAccountId, final ServletUriComponentsBuilder servletUriComponentsBuilder) {
        final Mono<BalanceInquiryResponse> sorResponse = getSorResponse(savingsAccountId, AccountType.SV.toCode());

        return sorResponse.map(response -> {
            final SavingAccountFacility savingsAccountFacility = new SavingAccountFacility();
            savingsAccountFacility.setAccountFacility(getAccountFacility(response));
            final Identifier identifier = new Identifier();
            identifier.setIdentifierFieldValue(new FieldValue(response.getDepAcctId().getAcctId()));
            final AccountIdentification accountIdentification = new AccountIdentification();
            accountIdentification.setIdentifier(identifier);
            //setting account number
            savingsAccountFacility.setSavingAccountNumber(accountIdentification);
            final SavingAccountFacilityResponse savingAccountFacilityResponse = new
                    SavingAccountFacilityResponse(savingsAccountFacility);
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));

            return objectMapper.convertValue(new SavingBalanceInquiryResponse(savingAccountFacilityResponse,
                    getLinks(servletUriComponentsBuilder), getMeta()), SavingBalanceInquiryResponse.class);
        });
    }

    /**
     * This method returning getting Sor Response and getting Time Deposit Balance Response
     *
     * @param timeDepositId
     * @param servletUriComponentsBuilder
     * @return TimeDepositBalanceInquiryResponse
     */
    @Override
    @MethodLogger
    public Mono<TimeDepositBalanceInquiryResponse> getTimeDepositAccountBalance(
            final String timeDepositId, final ServletUriComponentsBuilder servletUriComponentsBuilder) {
        final Mono<BalanceInquiryResponse> sorResponse = getSorResponse(timeDepositId, AccountType.TM.toCode());
        return sorResponse.map(response -> {
            final TimeDepositAccountFacility timeDepositAccountFacility = new TimeDepositAccountFacility();
            timeDepositAccountFacility.setTimeDepositAccountNumber(response.getDepAcctId().getAcctId());
            timeDepositAccountFacility.setAccountType(response.getDepAcctId().getAcctType());
            timeDepositAccountFacility.setAccountBalance(getAccountBalances(response));
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
            final TimeDepositAccountFacilityResponse timeDepositAccountFacilityResponse =
                    new TimeDepositAccountFacilityResponse(timeDepositAccountFacility);
            return objectMapper.convertValue(new TimeDepositBalanceInquiryResponse(timeDepositAccountFacilityResponse,
                    getLinks(servletUriComponentsBuilder), getMeta()), TimeDepositBalanceInquiryResponse.class);
        });
    }

    /**
     * Mapping Sor Balance Response to AccountFacility
     *
     * @param response
     * @return List<AccountBalances>
     */

    @MethodLogger
    private AccountFacility getAccountFacility(final BalanceInquiryResponse response) {
        final AccountFacility currentAccountFacility = new AccountFacility();
        final AccountTypes accountType = new AccountTypes();
        accountType.setAccountType(response.getDepAcctId().getAcctType());
        //setting account type
        currentAccountFacility.setAccountType(accountType);
        final Account account = new Account();
        account.setAccountBalance(getAccountBalances(response));
        currentAccountFacility.setLinkedAccounts(account);
        return currentAccountFacility;
    }

    /**
     * This method getting Sor response
     *
     * @param accountId
     * @param accountType
     * @return BalanceInquiryResponse
     */

    @MethodLogger
    private Mono<BalanceInquiryResponse> getSorResponse(
            final String accountId, final String accountType) {
        final DepartmentAccountId departmentAccountId = new DepartmentAccountId(accountId, accountType);
        final BalanceInquiryRequest sorRequest = new BalanceInquiryRequest(departmentAccountId, true, true);
        return balanceInquiryClient.getBalanceInquiry(sorRequest);
    }

    /**
     * This method returning getting Sor Response and getting loan Account Balance Response
     *
     * @param loanAccountId
     * @param servletUriComponentsBuilder
     * @return LoanBalanceInquiryResponse
     */
    @Override
    @SneakyThrows
    @MethodLogger
    public Mono<LoanBalanceInquiryResponse> getLoanAccountBalance(
            final String loanAccountId, final ServletUriComponentsBuilder servletUriComponentsBuilder) {
        final Mono<LoanBalanceInquiryResponseSor> sorResponse = getLoanSorResponse(
                loanAccountId, AccountType.LN.toCode());
        return sorResponse.map(response -> {
            final RetrieveLoanFacilityResponse loanFacility = new RetrieveLoanFacilityResponse();
            loanFacility.setLoanFacility(getLoanAccountFacility(response));
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
            return objectMapper.convertValue(new LoanBalanceInquiryResponse(loanFacility,
                    getLinks(servletUriComponentsBuilder), getMeta()), LoanBalanceInquiryResponse.class);
        });
    }

    /**
     * Mapping Loan Sor Balance Response to RetrieveLoanFacilityResponseLoanFacility
     *
     * @param response
     * @return List<AccountBalances>
     */
    @MethodLogger
    private RetrieveLoanFacilityResponseLoanFacility getLoanAccountFacility(
            final LoanBalanceInquiryResponseSor response) {
        final RetrieveLoanFacilityResponseLoanFacility loanFacility = new RetrieveLoanFacilityResponseLoanFacility();
        loanFacility.setLoanNumber(response.getLoanAccountId().getAcctId());

        //setting account type
        loanFacility.setAccountBalance(response.getAcctBals().stream().map(e -> {
            final Accountbalances accountBalances = new Accountbalances();
            //setting account balance type
            accountBalances.setAccountBalanceType(e.getAmountBalanceType());
            final Amount accountBalance = new Amount();
            final Currencycode currencyCode = new Currencycode(e.getAmountCurrency().getCurrencycode());
            //setting amount currency
            accountBalance.setAmountCurrency(currencyCode);
            //setting amount value
            accountBalance.setAmountValue(String.valueOf(e.getAmountCurrency().getAmountValue()));
            accountBalances.setAccountBalance(accountBalance);
            return accountBalances;
        }).collect(Collectors.toList()));
        return loanFacility;
    }

    /**
     * This method getting Loan Sor response
     *
     * @param accountId
     * @param accountType
     * @return LoanBalanceInquiryResponse
     */

    @MethodLogger
    private Mono<LoanBalanceInquiryResponseSor> getLoanSorResponse(
            final String accountId, final String accountType) {
        final LoanAccountId loanAccountId = new LoanAccountId(accountId, accountType);
        final LoanBalanceInquiryRequestSor sorRequest = new LoanBalanceInquiryRequestSor(loanAccountId, true, true);
        return balanceInquiryClient.getLoanBalanceInquiry(sorRequest);
    }

    /**
     * Creating Links for response
     *
     * @return Links
     */
    @MethodLogger
    private Links getLinks(final ServletUriComponentsBuilder servletUriComponentsBuilder) {
        //Creating Links
        return Links.builder()
                .first(EvolutionConstantUtils.EMPTY_STRING)
                .last(EvolutionConstantUtils.EMPTY_STRING)
                .next(EvolutionConstantUtils.EMPTY_STRING)
                .prev(EvolutionConstantUtils.EMPTY_STRING)
                .self(servletUriComponentsBuilder.build().toString())
                .build();
    }

    /**
     * Creating Meta for response
     *
     * @return Meta
     */
    @MethodLogger
    private Meta getMeta() {
        //Creating Meta
        return Meta.builder()
                .totalPages(EvolutionConstantUtils.ONE)
                .firstAvailableDateTime(EvolutionConstantUtils.EMPTY_STRING)
                .lastAvailableDateTime(EvolutionConstantUtils.EMPTY_STRING)
                .build();
    }

    @MethodLogger
    private List<AccountBalances> getAccountBalances(final BalanceInquiryResponse response) {
        return response.getAcctBals().stream().map(e -> {
            final AccountBalances accountBalances = new AccountBalances();
            //setting account balance type
            accountBalances.setAccountBalanceType(e.getAmountBalanceType());
            final AccountBalance accountBalance = new AccountBalance();
            final CurrencyCode currencyCode = new CurrencyCode(e.getAmountCurrency().getCurrencycode());
            //setting amount currency
            accountBalance.setAmountCurrency(currencyCode);
            //setting amount value
            accountBalance.setAmountValue(String.valueOf(e.getAmountCurrency().getAmountValue()));
            accountBalances.setAccountBalance(accountBalance);
            return accountBalances;
        }).collect(Collectors.toList());
    }
}

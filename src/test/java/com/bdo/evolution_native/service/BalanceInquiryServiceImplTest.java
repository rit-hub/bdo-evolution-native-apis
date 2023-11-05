package com.bdo.evolution_native.service;

import com.bdo.evolution_native.JsonReader;
import com.bdo.evolution_native.client.BalanceInquiryClient;
import com.bdo.evolution_native.client.model.BalanceInquiryRequest;
import com.bdo.evolution_native.client.model.BalanceInquiryResponse;
import com.bdo.evolution_native.client.model.LoanBalanceInquiryRequestSor;
import com.bdo.evolution_native.client.model.LoanBalanceInquiryResponseSor;
import com.bdo.evolution_native.model.CurrentBalanceInquiryResponse;
import com.bdo.evolution_native.model.SavingBalanceInquiryResponse;
import com.bdo.evolution_native.model.TimeDepositBalanceInquiryResponse;
import com.bdo.evolution_native.model.loan.model.LoanBalanceInquiryResponse;
import com.bdo.evolution_native.service.impl.BalanceInquiryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@ExtendWith(MockitoExtension.class)
class BalanceInquiryServiceImplTest {

    @Value("${Current-account.request.CurrentAccountId}")
    private String currentAccountId;
    @Value("${Current-account.response.file-path}")
    private String responseFilePath;

    @Value("${Current-account.sor.request.file-path}")
    private String sorRequestFilePath;
    @Value("${Current-account.sor.response.file-path}")
    private String sorResponseFilePath;

    @Value("${Savings-account.request.SavingsAccountId}")
    private String savingsAccountId;
    @Value("${Savings-account.response.file-path}")
    private String responseSavingsFilePath;
    @Value("${Savings-account.sor.response.file-path}")
    private String sorSavingsResponseFilePath;
    @Value("${Savings-account.sor.request.file-path}")
    private String sorSavingsRequestFilePath;

    @Value("${Time-deposit.request.TimeDepositId}")
    private String timeDepositId;
    @Value("${Time-deposit.response.file-path}")
    private String responseTimeDepositFilePath;
    @Value("${Time-deposit.sor.response.file-path}")
    private String sorTimeDepositResponseFilePath;
    @Value("${Time-deposit.sor.request.file-path}")
    private String sorTimeDepositRequestFilePath;

    @Mock
    private BalanceInquiryClient balanceInquiryClient;

    @InjectMocks
    private BalanceInquiryServiceImpl balanceInquiryService;
    private LoanBalanceInquiryResponse loanAccountBalanceInquiryResponse;
    private LoanBalanceInquiryRequestSor loanBalanceInquiryRequestSor;
    private LoanBalanceInquiryResponseSor loanAccountBalanceInquiryResponseSor;

    @BeforeEach
    void setup() throws IOException {
        MockitoAnnotations.openMocks(this);
        loanAccountBalanceInquiryResponse = (LoanBalanceInquiryResponse) new JsonReader<>(LoanBalanceInquiryResponse.class).loadTestJson("Loan-Account/loan-account-response.json");
        loanBalanceInquiryRequestSor = (LoanBalanceInquiryRequestSor) new JsonReader<>(LoanBalanceInquiryRequestSor.class).loadTestJson("Loan-Account/loan-account-sor-request.json");
        loanAccountBalanceInquiryResponseSor = (LoanBalanceInquiryResponseSor) new JsonReader<>(LoanBalanceInquiryResponseSor.class).loadTestJson("Loan-Account/loan-account-sor-response.json");
    }

    @Test
    void testCurrentAccount() throws IOException {
        BalanceInquiryRequest balanceInquiryRequest = (BalanceInquiryRequest) new JsonReader<>(BalanceInquiryRequest.class).loadTestJson(sorRequestFilePath);
        BalanceInquiryResponse sorResponse = (BalanceInquiryResponse) new JsonReader<>(BalanceInquiryResponse.class).loadTestJson(sorResponseFilePath);
        CurrentBalanceInquiryResponse response = (CurrentBalanceInquiryResponse) new JsonReader<>(CurrentBalanceInquiryResponse.class).loadTestJson(responseFilePath);
        Mockito.when(balanceInquiryClient.getBalanceInquiry(balanceInquiryRequest)).thenReturn(Mono.just(sorResponse));
        Mono<CurrentBalanceInquiryResponse> currentAccountBalance = balanceInquiryService.getCurrentAccountBalance(currentAccountId, ServletUriComponentsBuilder.fromCurrentRequest());
        currentAccountBalance.subscribe(result -> assertEquals(response, result)).dispose();
    }

    @Test
    void testSavingsAccount() throws IOException {
        BalanceInquiryRequest balanceInquiryRequest = (BalanceInquiryRequest) new JsonReader<>(BalanceInquiryRequest.class).loadTestJson(sorSavingsRequestFilePath);
        BalanceInquiryResponse sorResponse = (BalanceInquiryResponse) new JsonReader<>(BalanceInquiryResponse.class).loadTestJson(sorSavingsResponseFilePath);
        SavingBalanceInquiryResponse response = (SavingBalanceInquiryResponse) new JsonReader<>(SavingBalanceInquiryResponse.class).loadTestJson(responseSavingsFilePath);
        Mockito.when(balanceInquiryClient.getBalanceInquiry(balanceInquiryRequest)).thenReturn(Mono.just(sorResponse));
        Mono<SavingBalanceInquiryResponse> savingsAccountBalance = balanceInquiryService.getSavingsAccountBalance(savingsAccountId, ServletUriComponentsBuilder.fromCurrentRequest());
        savingsAccountBalance.subscribe(result -> assertEquals(response, result)).dispose();
    }

    @Test
    void testTimeDeposit() throws IOException {
        BalanceInquiryRequest balanceInquiryRequest = (BalanceInquiryRequest) new JsonReader<>(BalanceInquiryRequest.class).loadTestJson(sorTimeDepositRequestFilePath);
        BalanceInquiryResponse sorResponse = (BalanceInquiryResponse) new JsonReader<>(BalanceInquiryResponse.class).loadTestJson(sorTimeDepositResponseFilePath);
        TimeDepositBalanceInquiryResponse response = (TimeDepositBalanceInquiryResponse) new JsonReader<>(TimeDepositBalanceInquiryResponse.class).loadTestJson(responseTimeDepositFilePath);
        Mockito.when(balanceInquiryClient.getBalanceInquiry(balanceInquiryRequest)).thenReturn(Mono.just(sorResponse));
        Mono<TimeDepositBalanceInquiryResponse> timeDepositAccountBalanceBalance = balanceInquiryService.getTimeDepositAccountBalance(savingsAccountId, ServletUriComponentsBuilder.fromCurrentRequest());
        timeDepositAccountBalanceBalance.subscribe(result -> assertEquals(response, result)).dispose();
    }

    @Test
    void testLoanAccountBalance() throws IOException {
        Mockito.when(balanceInquiryClient.getLoanBalanceInquiry(any())).thenReturn(Mono.just(loanAccountBalanceInquiryResponseSor));
        Mono<LoanBalanceInquiryResponse> loanAccountBalance = balanceInquiryService.getLoanAccountBalance("800010001238", ServletUriComponentsBuilder.fromCurrentRequest());
        loanAccountBalance.subscribe(result -> assertEquals(loanAccountBalanceInquiryResponse, result)).dispose();
        assertNotNull(loanAccountBalance);
    }
}

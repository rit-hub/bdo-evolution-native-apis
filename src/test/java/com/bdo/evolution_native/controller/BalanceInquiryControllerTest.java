package com.bdo.evolution_native.controller;

import com.bdo.evolution_native.JsonReader;
import com.bdo.evolution_native.model.CurrentBalanceInquiryResponse;
import com.bdo.evolution_native.model.SavingBalanceInquiryResponse;
import com.bdo.evolution_native.model.TimeDepositBalanceInquiryResponse;
import com.bdo.evolution_native.model.loan.model.LoanBalanceInquiryResponse;
import com.bdo.evolution_native.service.BalanceInquiryService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@ExtendWith(MockitoExtension.class)
class BalanceInquiryControllerTest {
    @Value("${Current-account.response.file-path}")
    private String responseFilePath;

    @Value("${Current-account.request.CurrentAccountId}")
    private String currentAccountId;

    @Value("${request.authorization}")
    private String authorization;

    @Value("${request.fapi-auth-date}")
    private String xFapiAuthDate;

    @Value("${request.customer-ip}")
    private String xFapiCustomerIpAddress;

    @Value("${request.interaction-id}")
    private String xFapiInteractionId;

    @Value("${request.customer-user-agent}")
    private String xCustomerUserAgent;

    @Value("${Savings-account.response.file-path}")
    private String responseSavingsFilePath;

    @Value("${Savings-account.request.SavingsAccountId}")
    private String savingsAccountId;

    @Value("${Time-deposit.response.file-path}")
    private String responseTimeDepositFilePath;

    @Value("${Time-deposit.request.TimeDepositId}")
    private String timeDepositId;

    @Mock
    private BalanceInquiryService balanceInquiryService;

    @InjectMocks
    private BalanceInquiryController balanceInquiryController;

    private CurrentBalanceInquiryResponse currentBalanceInquiryResponse;

    private SavingBalanceInquiryResponse savingBalanceInquiryResponse;

    private TimeDepositBalanceInquiryResponse timeDepositBalanceInquiryResponse;

    private LoanBalanceInquiryResponse loanAccountBalanceInquiryResponse;

    @BeforeEach
    void setup() throws IOException {
        MockitoAnnotations.openMocks(this);
        balanceInquiryController = new BalanceInquiryController(balanceInquiryService);
        currentBalanceInquiryResponse = (CurrentBalanceInquiryResponse) new JsonReader<>(CurrentBalanceInquiryResponse.class).loadTestJson(responseFilePath);
        savingBalanceInquiryResponse = (SavingBalanceInquiryResponse) new JsonReader<>(SavingBalanceInquiryResponse.class).loadTestJson(responseSavingsFilePath);
        timeDepositBalanceInquiryResponse = (TimeDepositBalanceInquiryResponse) new JsonReader<>(TimeDepositBalanceInquiryResponse.class).loadTestJson(responseTimeDepositFilePath);
        loanAccountBalanceInquiryResponse = (LoanBalanceInquiryResponse) new JsonReader<>(LoanBalanceInquiryResponse.class).loadTestJson("Loan-Account/loan-account-response.json");
    }

    @Test
    void testGetCurrentAccountBalance() throws IOException {
        Mockito.when(balanceInquiryService.getCurrentAccountBalance(Mockito.any(), Mockito.any())).thenReturn(Mono.just(currentBalanceInquiryResponse));
        Mono<ResponseEntity<CurrentBalanceInquiryResponse>> actualResponse = balanceInquiryController.getCurrentAccountBalance(currentAccountId,
                xFapiAuthDate, xFapiCustomerIpAddress, xFapiInteractionId, xCustomerUserAgent);
        actualResponse.subscribe(response -> assertEquals(currentBalanceInquiryResponse,
                response.getBody()));
    }

    @Test
    void testGetSavingsAccountBalance() throws IOException {
        Mockito.when(balanceInquiryService.getSavingsAccountBalance(Mockito.any(), Mockito.any())).thenReturn(Mono.just(savingBalanceInquiryResponse));
        Mono<ResponseEntity<SavingBalanceInquiryResponse>> actualResponse = balanceInquiryController.getSavingsAccountBalance(savingsAccountId,
                xFapiAuthDate, xFapiCustomerIpAddress, xFapiInteractionId, xCustomerUserAgent);
        actualResponse.subscribe(response -> assertEquals(savingBalanceInquiryResponse,
                response.getBody()));
    }

    @Test
    void testGetTimeDepositAccountBalance() throws IOException {
        Mockito.when(balanceInquiryService.getTimeDepositAccountBalance(Mockito.any(), Mockito.any())).thenReturn(Mono.just(timeDepositBalanceInquiryResponse));
        Mono<ResponseEntity<TimeDepositBalanceInquiryResponse>> actualResponse = balanceInquiryController.getTimeDepositAccountBalance(savingsAccountId,
                xFapiAuthDate, xFapiCustomerIpAddress, xFapiInteractionId, xCustomerUserAgent);
        actualResponse.subscribe(response -> assertEquals(timeDepositBalanceInquiryResponse,
                response.getBody()));
    }

    @Test
    void testGetLoanAccountBalance() throws IOException {
        Mockito.when(balanceInquiryService.getLoanAccountBalance(Mockito.any(), Mockito.any())).thenReturn(Mono.just(loanAccountBalanceInquiryResponse));
        Mono<ResponseEntity<LoanBalanceInquiryResponse>> actualResponse = balanceInquiryController.getLoanAccountBalance("12345",
                xFapiAuthDate, xFapiCustomerIpAddress, xFapiInteractionId, xCustomerUserAgent);
        actualResponse.subscribe(response -> assertEquals(loanAccountBalanceInquiryResponse,
                response.getBody()));
    }
}

package com.bdo.evolution_native.client;

import com.bdo.evolution_native.JsonReader;
import com.bdo.evolution_native.client.model.BalanceInquiryRequest;
import com.bdo.evolution_native.client.model.BalanceInquiryResponse;
import com.bdo.evolution_native.client.model.LoanBalanceInquiryRequestSor;
import com.bdo.evolution_native.client.model.LoanBalanceInquiryResponseSor;
import com.bdo.evolution_native.client.model.TokenResponse;
import com.bdo.evolution_native.config.TokenClientConfig;
import com.bdo.evolution_native.config.WebClientConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@ExtendWith(MockitoExtension.class)
class BalanceInquiryClientTest {

    @Mock
    private TokenManagerClient tokenManagerClient;
    private MockWebServer mockWebServer;
    @Value("${Current-account.sor.request.file-path}")
    private String sorRequestFilePath;
    @Value("${Current-account.sor.response.file-path}")
    private String sorResponseFilePath;

    @Value("${SOR-Error.error-response.file-path-PS10040}")
    private String sorErrorResponseFilePath;

    @Value("${token.response-file}")
    private String tokenFile;

    private BalanceInquiryClient balanceInquiryClient;

    private TokenResponse tokenResponse;

    @BeforeEach
    void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(6789);
        balanceInquiryClient = new BalanceInquiryClient(mockWebServer.url("/balance").toString(), new WebClientConfig(), tokenManagerClient);
        tokenResponse = new JsonReader<>(TokenResponse.class).loadTestJson(tokenFile);
    }

    @Test
    void testGetBalanceInquiry() throws IOException {
        BalanceInquiryResponse expectedResponse = (BalanceInquiryResponse) new JsonReader<>(BalanceInquiryResponse.class).loadTestJson(sorResponseFilePath);
        BalanceInquiryRequest sorCreationRequest = (BalanceInquiryRequest) new JsonReader<>(BalanceInquiryRequest.class).loadTestJson(sorRequestFilePath);
        when(tokenManagerClient.getAccessToken()).thenReturn(Mono.just("1e4a0c19-2467-4d6f-a6b9-c77653af6b42"));
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody(new ObjectMapper().writeValueAsString(expectedResponse)));
        Mono<BalanceInquiryResponse> actualResponse = balanceInquiryClient.getBalanceInquiry(sorCreationRequest);
        actualResponse.subscribe(response -> assertEquals(expectedResponse, response)).dispose();
    }

    @Test
    void testGetBalanceInquiryWithErrors() throws IOException {
        BalanceInquiryResponse expectedResponse = (BalanceInquiryResponse) new JsonReader<>(BalanceInquiryResponse.class).loadTestJson(sorErrorResponseFilePath);
        BalanceInquiryRequest sorCreationRequest = (BalanceInquiryRequest) new JsonReader<>(BalanceInquiryRequest.class).loadTestJson(sorRequestFilePath);
        when(tokenManagerClient.getAccessToken()).thenReturn(Mono.just("1e4a0c19-2467-4d6f-a6b9-c77653af6b42"));
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody(new ObjectMapper().writeValueAsString(expectedResponse)));
        Mono<BalanceInquiryResponse> actualResponse = balanceInquiryClient.getBalanceInquiry(sorCreationRequest);
        actualResponse.subscribe(response -> assertEquals(expectedResponse, response)).dispose();
    }

    @Test
    void testGetLoanBalanceInquiry() throws IOException {
        LoanBalanceInquiryResponseSor loanAccountBalanceInquiryResponseSor = (LoanBalanceInquiryResponseSor) new JsonReader<>(LoanBalanceInquiryResponseSor.class).loadTestJson("Loan-Account/loan-account-sor-response.json");
        LoanBalanceInquiryRequestSor loanBalanceInquiryRequestSor = (LoanBalanceInquiryRequestSor) new JsonReader<>(LoanBalanceInquiryRequestSor.class).loadTestJson("Loan-Account/loan-account-sor-request.json");
        when(tokenManagerClient.getAccessToken()).thenReturn(Mono.just("1e4a0c19-2467-4d6f-a6b9-c77653af6b42"));
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody(new ObjectMapper().writeValueAsString(loanAccountBalanceInquiryResponseSor)));
        Mono<LoanBalanceInquiryResponseSor> actualResponse = balanceInquiryClient.getLoanBalanceInquiry(loanBalanceInquiryRequestSor);
        actualResponse.subscribe(response -> assertEquals(loanAccountBalanceInquiryResponseSor, response)).dispose();
    }

    @Test
    void testGetLoanBalanceInquiryWithError() throws IOException {
        LoanBalanceInquiryResponseSor expectedResponse = (LoanBalanceInquiryResponseSor) new JsonReader<>(LoanBalanceInquiryResponseSor.class).loadTestJson("Error_Response_from_SOR/sor-error-PS10040.json");
        LoanBalanceInquiryRequestSor loanBalanceInquiryRequestSor = (LoanBalanceInquiryRequestSor) new JsonReader<>(LoanBalanceInquiryRequestSor.class).loadTestJson("Loan-Account/loan-account-sor-request.json");
        when(tokenManagerClient.getAccessToken()).thenReturn(Mono.just("1e4a0c19-2467-4d6f-a6b9-c77653af6b42"));
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody(new ObjectMapper().writeValueAsString(expectedResponse)));
        Mono<LoanBalanceInquiryResponseSor> actualResponse = balanceInquiryClient.getLoanBalanceInquiry(loanBalanceInquiryRequestSor);
        actualResponse.subscribe(response -> assertEquals(expectedResponse, actualResponse.block())).dispose();
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }
}

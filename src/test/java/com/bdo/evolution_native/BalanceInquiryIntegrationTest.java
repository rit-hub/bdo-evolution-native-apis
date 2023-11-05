package com.bdo.evolution_native;

import com.bdo.evolution_native.client.model.BalanceInquiryResponse;
import com.bdo.evolution_native.client.model.LoanBalanceInquiryResponseSor;
import com.bdo.evolution_native.client.model.TokenResponse;
import com.bdo.evolution_native.model.CurrentBalanceInquiryResponse;
import com.bdo.evolution_native.model.SavingBalanceInquiryResponse;
import com.bdo.evolution_native.model.TimeDepositBalanceInquiryResponse;
import com.bdo.evolution_native.model.loan.model.LoanBalanceInquiryResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.json.JSONException;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
class BalanceInquiryIntegrationTest {
    @LocalServerPort
    private Integer port;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${request.fapi-auth-date}")
    private String authDate;

    @Value("${request.customer-ip}")
    private String ipAddress;
    @Value("${request.interaction-id}")
    private String interactionId;
    @Value("${request.customer-user-agent}")
    private String userAgent;
    @Value("${request.authorization}")
    private String authorization;
    @Value("${Current-account.sor.response.file-path}")
    private String sorResponseFilePath;
    @Value("${Current-account.response.file-path}")
    private String responseFilePath;

    @Value("${SOR-Error.error-response.file-path-PS10040}")
    private String sorErrorPs10040FilePath;

    @Value("${SOR-Error.error-response.file-path-HC00052}")
    private String sorErrorHc00052FilePath;

    @Value("${SOR-Error.error-response.file-path-PS10032}")
    private String sorErrorPs10032FilePath;

    @Value("${SOR-Error.error-response.file-path-PS10107}")
    private String sorErrorPs10107FilePath;

    @Value("${SOR-Error.error-response.file-path-PS10187}")
    private String sorErrorPs10187FilePath;

    @Value("${Current-account.request.CurrentAccountId}")
    private String currentAccountId;

    @Value("${Savings-account.request.SavingsAccountId}")
    private String savingsAccountId;
    @Value("${Savings-account.sor.response.file-path}")
    private String sorSavingsResponseFilePath;
    @Value("${Savings-account.response.file-path}")

    private String responseSavingsFilePath;

    @Value("${Time-deposit.request.TimeDepositId}")
    private String timeDepositId;

    @Value("${token.response-file}")
    private String tokenFile;

    @Value("${Time-deposit.sor.response.file-path}")
    private String sorTimeDepositResponseFilePath;
    @Value("${Time-deposit.response.file-path}")
    private String responseTimeDepositFilePath;

    private MockWebServer mockWebServer;
    private MockWebServer mockWebServer2;

    @BeforeEach
    void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(9998);
        mockWebServer.url("/balance");
        mockWebServer2 = new MockWebServer();
        mockWebServer2.start(9999);
        mockWebServer2.url("/token");
        TokenResponse token = new JsonReader<>(TokenResponse.class).loadTestJson(tokenFile);
        mockWebServer2.enqueue(new MockResponse().setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody(new ObjectMapper().writeValueAsString(token)));
        RestAssured.port = this.port;
    }

    // Current
    @Test
    void currentIntegrationTest() throws IOException {
        BalanceInquiryResponse expectedResponse = (BalanceInquiryResponse) new JsonReader<>(BalanceInquiryResponse.class).loadTestJson(sorResponseFilePath);
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody(new ObjectMapper().writeValueAsString(expectedResponse)));
        final CurrentBalanceInquiryResponse response = RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("CurrentAccountId", currentAccountId)
                .get(contextPath + "/CurrentAccount/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .extract()
                .as(CurrentBalanceInquiryResponse.class);
        CurrentBalanceInquiryResponse balanceInquiryResponse = (CurrentBalanceInquiryResponse) new JsonReader<>(CurrentBalanceInquiryResponse.class).loadTestJson(responseFilePath);
        assertEquals(balanceInquiryResponse.getData(), response.getData());
    }

    @Test
    void currentIntegrationTest400FromSOR() throws IOException, JSONException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(400)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody("400 From SOR"));
        RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("CurrentAccountId", currentAccountId)
                .get(contextPath + "/CurrentAccount/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(400)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void currentIntegrationTest404FromSOR() throws IOException, JSONException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(404)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody("404 From SOR"));
        RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("CurrentAccountId", currentAccountId)
                .get(contextPath + "/CurrentAccount/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(404)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void integrationTest500FromSOR() throws IOException, JSONException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody("500 From SOR"));
        RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("CurrentAccountId", currentAccountId)
                .get(contextPath + "/CurrentAccount/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(500)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    //Savings
    @Test
    void savingsIntegrationTest() throws IOException, JSONException {
        BalanceInquiryResponse expectedResponse = (BalanceInquiryResponse) new JsonReader<>(BalanceInquiryResponse.class).loadTestJson(sorSavingsResponseFilePath);
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody(new ObjectMapper().writeValueAsString(expectedResponse)));
        final SavingBalanceInquiryResponse response = RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("SavingsAccountId", savingsAccountId)
                .get(contextPath + "/SavingsAccount/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .extract()
                .as(SavingBalanceInquiryResponse.class);
        SavingBalanceInquiryResponse balanceInquiryResponse = (SavingBalanceInquiryResponse) new JsonReader<>(SavingBalanceInquiryResponse.class).loadTestJson(responseSavingsFilePath);
        assertEquals(balanceInquiryResponse.getData(), response.getData());
    }

    @Test
    void savingsIntegrationTestWithErrorPs10187() throws IOException, JSONException {
        BalanceInquiryResponse expectedResponse = (BalanceInquiryResponse) new JsonReader<>(BalanceInquiryResponse.class).loadTestJson(sorErrorPs10187FilePath);
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody(new ObjectMapper().writeValueAsString(expectedResponse)));
        RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("SavingsAccountId", savingsAccountId)
                .get(contextPath + "/SavingsAccount/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(400)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void savingsIntegrationTest400FromSOR() throws IOException, JSONException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(400)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody("400 From SOR"));
        RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("SavingsAccountId", savingsAccountId)
                .get(contextPath + "/SavingsAccount/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(400)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void savingsIntegrationTest404FromSOR() throws IOException, JSONException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(404)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody("404 From SOR"));
        RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("SavingsAccountId", savingsAccountId)
                .get(contextPath + "/SavingsAccount/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(404)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void savingsIntegrationTest500FromSOR() throws IOException, JSONException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody("500 From SOR"));
        RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("SavingsAccountId", savingsAccountId)
                .get(contextPath + "/SavingsAccount/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(500)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    //Time-Deposit
    @Test
    void timeDepositIntegrationTest() throws IOException, JSONException {
        BalanceInquiryResponse expectedResponse = (BalanceInquiryResponse) new JsonReader<>(BalanceInquiryResponse.class).loadTestJson(sorTimeDepositResponseFilePath);
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody(new ObjectMapper().writeValueAsString(expectedResponse)));
        final TimeDepositBalanceInquiryResponse response = RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("TimeDepositId", timeDepositId)
                .get(contextPath + "/TimeDeposit/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .extract()
                .as(TimeDepositBalanceInquiryResponse.class);
        TimeDepositBalanceInquiryResponse balanceInquiryResponse = (TimeDepositBalanceInquiryResponse) new JsonReader<>(TimeDepositBalanceInquiryResponse.class).loadTestJson(responseTimeDepositFilePath);
        assertEquals(balanceInquiryResponse.getData(), response.getData());
    }

    @Test
    void timeDepositIntegrationTest400FromSOR() throws IOException, JSONException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(400)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody("400 From SOR"));
        RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("TimeDepositId", timeDepositId)
                .get(contextPath + "/TimeDeposit/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(400)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void timeDepositIntegrationTest404FromSOR() throws IOException, JSONException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(404)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody("404 From SOR"));
        RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("TimeDepositId", timeDepositId)
                .get(contextPath + "/TimeDeposit/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(404)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void timeDepositIntegrationTest500FromSOR() throws IOException, JSONException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody("500 From SOR"));
        RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("TimeDepositId", timeDepositId)
                .get(contextPath + "/TimeDeposit/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(500)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    //Host-Error Codes
    @Test
    void integrationTestWithErrorPs10040() throws IOException, JSONException {
        BalanceInquiryResponse expectedResponse = (BalanceInquiryResponse) new JsonReader<>(BalanceInquiryResponse.class).loadTestJson(sorErrorPs10040FilePath);
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody(new ObjectMapper().writeValueAsString(expectedResponse)));
        RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("CurrentAccountId", currentAccountId)
                .get(contextPath + "/CurrentAccount/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(400)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void integrationTestWithErrorHc00052() throws IOException, JSONException {
        BalanceInquiryResponse expectedResponse = (BalanceInquiryResponse) new JsonReader<>(BalanceInquiryResponse.class).loadTestJson(sorErrorHc00052FilePath);
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody(new ObjectMapper().writeValueAsString(expectedResponse)));
        RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("CurrentAccountId", currentAccountId)
                .get(contextPath + "/CurrentAccount/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(500)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void integrationTestWithErrorPs10032() throws IOException, JSONException {
        BalanceInquiryResponse expectedResponse = (BalanceInquiryResponse) new JsonReader<>(BalanceInquiryResponse.class).loadTestJson(sorErrorPs10032FilePath);
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody(new ObjectMapper().writeValueAsString(expectedResponse)));
        RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("CurrentAccountId", currentAccountId)
                .get(contextPath + "/CurrentAccount/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(400)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void integrationTestWithErrorPs10107() throws IOException, JSONException {
        BalanceInquiryResponse expectedResponse = (BalanceInquiryResponse) new JsonReader<>(BalanceInquiryResponse.class).loadTestJson(sorErrorPs10107FilePath);
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody(new ObjectMapper().writeValueAsString(expectedResponse)));
        RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("CurrentAccountId", currentAccountId)
                .get(contextPath + "/CurrentAccount/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(500)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void integrationTestWithErrorPs10187() throws IOException, JSONException {
        BalanceInquiryResponse expectedResponse = (BalanceInquiryResponse) new JsonReader<>(BalanceInquiryResponse.class).loadTestJson(sorErrorPs10187FilePath);
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody(new ObjectMapper().writeValueAsString(expectedResponse)));
        RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("CurrentAccountId", currentAccountId)
                .get(contextPath + "/CurrentAccount/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(400)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    // Loan Account
    @Test
    void loanIntegrationTest() throws IOException, JSONException {
        LoanBalanceInquiryResponseSor expectedResponse = (LoanBalanceInquiryResponseSor) new JsonReader<>(LoanBalanceInquiryResponseSor.class).loadTestJson("Loan-Account/loan-account-sor-response.json");
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody(new ObjectMapper().writeValueAsString(expectedResponse)));
        final LoanBalanceInquiryResponse response = RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("LoanId", "12345")
                .get(contextPath + "/Loan/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .extract()
                .as(LoanBalanceInquiryResponse.class);
        LoanBalanceInquiryResponse loanBalanceInquiryResponse = (LoanBalanceInquiryResponse) new JsonReader<>(LoanBalanceInquiryResponse.class).loadTestJson("Loan-Account/loan-account-response.json");
        assertEquals(loanBalanceInquiryResponse.getData(), response.getData());
    }

    @Test
    void loanIntegrationTest400FromSOR() throws IOException, JSONException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(400)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody("400 From SOR"));
        RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("LoanId", "12345")
                .get(contextPath + "/Loan/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(400)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void loanIntegrationTest404FromSOR() throws IOException, JSONException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(404)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody("404 From SOR"));
        RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("LoanId", "12345")
                .get(contextPath + "/Loan/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(404)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void loanIntegrationTest500FromSOR() throws IOException, JSONException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody("500 From SOR"));
        RestAssured.given()
                .header("Authorization", authorization)
                .header("x-fapi-auth-date", authDate)
                .header("x-fapi-customer-ip-address", ipAddress)
                .header("x-fapi-interaction-id", interactionId)
                .header("x-customer-user-agent", userAgent)
                .header("LoanId", "12345")
                .get(contextPath + "/Loan/Retrieve")
                .then().log().body(true)
                .assertThat()
                .statusCode(500)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }


    @AfterEach
    void tearDown() throws IOException {
        mockWebServer2.shutdown();
        mockWebServer.shutdown();
    }

}

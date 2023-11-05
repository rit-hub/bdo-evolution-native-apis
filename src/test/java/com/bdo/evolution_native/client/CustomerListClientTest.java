package com.bdo.evolution_native.client;

import com.bdo.evolution_native.JsonReader;
import com.bdo.evolution_native.client.model.AccessTokenResponse;
import com.bdo.evolution_native.client.model.customerlist.CustListInqRq;
import com.bdo.evolution_native.client.model.customerlist.CustListInqRs;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


/**
 * The type Collateral right client test.
 */
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@ExtendWith(MockitoExtension.class)
class CustomerListClientTest {
    @Mock
    private TokenManagerClient tokenManagerClient;
    private MockWebServer mockWebServer;
    private CustomerListClient customerListClient;
    private CustListInqRs custListInqRs;

    private AccessTokenResponse accessTokenResponse;

    /**
     * Sets .
     *
     * @throws IOException the io exception
     */
    @BeforeEach
    void setup() throws IOException {
        custListInqRs = (CustListInqRs) new JsonReader<>(CustListInqRs.class).loadTestJson("CustListInqRs.json");
        mockWebServer = new MockWebServer();
        mockWebServer.start(9082);
        customerListClient = new CustomerListClient(mockWebServer.url("http://localhost:8080/parties/custlist/inq").toString(),
                new WebClientConfig(), tokenManagerClient);
//        accessTokenResponse = new JsonReader<>(AccessTokenResponse.class).loadTestJson("access-token-response.json");
    }

    /**
     * Test create collateral.
     *
     * @throws IOException the io exception
     */
    @Test
    void testCustomerClient() throws IOException {
        CustListInqRq sorCreationRequest = new CustListInqRq();
        sorCreationRequest.setDebitCardNumber("123456");
        when(tokenManagerClient.getAccessToken()).thenReturn(Mono.just("1e4a0c19-2467-4d6f-a6b9-c77653af6b42"));
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
            .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .setBody(new ObjectMapper().writeValueAsString(custListInqRs)));
        Mono<CustListInqRs> actualResponse = customerListClient.customerClient(sorCreationRequest);
        actualResponse.subscribe(response -> assertEquals(custListInqRs, response)).dispose();
    }

    /**
     * Tear down.
     *
     * @throws IOException the io exception
     */
    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }
}

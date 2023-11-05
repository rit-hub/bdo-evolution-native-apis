package com.bdo.evolution_native.client;

import com.bdo.evolution_native.JsonReader;
import com.bdo.evolution_native.client.model.custacctadd.AcctCustAddRq;
import com.bdo.evolution_native.client.model.custacctadd.AcctCustAddRs;
import com.bdo.evolution_native.client.model.employee.CustEmpAddRq;
import com.bdo.evolution_native.client.model.employee.CustEmpAddRs;
import com.bdo.evolution_native.config.WebClientConfig;
import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.bdo.evolution_native.exception.EvolutionException;
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
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


/**
 * The type Collateral right client test.
 */
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@ExtendWith(MockitoExtension.class)
class CustomerAccountInitiateClientTest {

    @Mock
    private WebClientConfig webClientConfig;
    @Mock
    private TokenManagerClient tokenManagerClient;
    private MockWebServer mockWebServer;
    private CustomerAccountInitiateClient customerAccountInitiateClient;
    private AcctCustAddRs acctCustAddRs;

    /**
     * Sets .
     *
     * @throws IOException the io exception
     */
    @BeforeEach
    void setup() throws IOException {
        acctCustAddRs = (AcctCustAddRs) new JsonReader<>(AcctCustAddRs.class).loadTestJson("Customer-Account-Initiate/AcctCustAddRs.json");
        mockWebServer = new MockWebServer();
        mockWebServer.start(9082);
        customerAccountInitiateClient = new CustomerAccountInitiateClient(mockWebServer
            .url("http://localhost:8080/customerposition/acctcust/add").toString(), new WebClientConfig(),
                tokenManagerClient);
    }

    /**
     * Test create collateral.
     *
     * @throws IOException the io exception
     */
    @Test
    void testCustomerClient() throws IOException {
        AcctCustAddRq sorCreationRequest = new AcctCustAddRq();
        when(tokenManagerClient.getAccessToken()).thenReturn(Mono.just("1e4a0c19-2467-4d6f-a6b9-c77653af6b42"));
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
            .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .setBody(new ObjectMapper().writeValueAsString(acctCustAddRs)));
        Mono<AcctCustAddRs> actualResponse = customerAccountInitiateClient.customerClient(sorCreationRequest);
        actualResponse.subscribe(response -> assertEquals(acctCustAddRs, response)).dispose();
    }
//    @Test
//    void testCustomerClientError() throws IOException {
//        acctCustAddRs.getStatus().setStatusCode(1);
//        AcctCustAddRq sorCreationRequest = new AcctCustAddRq();
//        when(tokenManagerClient.getAccessToken()).thenReturn(Mono.just("1e4a0c19-2467-4d6f-a6b9-c77653af6b42"));
//        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
//                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
//                .setBody(new ObjectMapper().writeValueAsString(acctCustAddRs)));
//        assertThrows(EvolutionException.class, ()->customerAccountInitiateClient.customerClient(sorCreationRequest).block());
////        actualResponse.subscribe(response -> assertEquals(acctCustAddRs, response)).dispose();
//    }




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

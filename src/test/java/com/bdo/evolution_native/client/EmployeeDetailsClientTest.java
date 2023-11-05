package com.bdo.evolution_native.client;

import com.bdo.evolution_native.JsonReader;
import com.bdo.evolution_native.client.model.TokenResponse;
import com.bdo.evolution_native.client.model.employee.CustEmpAddRq;
import com.bdo.evolution_native.client.model.employee.CustEmpAddRs;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


/**
 * The type Collateral right client test.
 */
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@ExtendWith(MockitoExtension.class)
class EmployeeDetailsClientTest {

    @Mock
    private TokenManagerClient tokenManagerClient;
    private MockWebServer mockWebServer;
    private EmployeeDetailsClient employeeDetailsClient;
    private CustEmpAddRs custEmpAddRs;

    /**
     * Sets .
     *
     * @throws IOException the io exception
     */
    @BeforeEach
    void setup() throws IOException {
        custEmpAddRs = (CustEmpAddRs) new JsonReader<>(CustEmpAddRs.class).loadTestJson("Employee-Add/CustEmpAddRs.json");
        mockWebServer = new MockWebServer();
        mockWebServer.start(9082);
        employeeDetailsClient = new EmployeeDetailsClient(mockWebServer
            .url("http://localhost:8080/parties/custemp/add").toString(), new WebClientConfig(),
                tokenManagerClient);
    }

    /**
     * Test create collateral.
     *
     * @throws IOException the io exception
     */
    @Test
    void testCustomerClient() throws IOException {
        CustEmpAddRq sorCreationRequest = new CustEmpAddRq();
        when(tokenManagerClient.getAccessToken()).thenReturn(Mono.just("1e4a0c19-2467-4d6f-a6b9-c77653af6b42"));
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
            .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .setBody(new ObjectMapper().writeValueAsString(custEmpAddRs)));
        Mono<CustEmpAddRs> actualResponse = employeeDetailsClient.employeeClient(sorCreationRequest);
        actualResponse.subscribe(response -> assertEquals(custEmpAddRs, response)).dispose();
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

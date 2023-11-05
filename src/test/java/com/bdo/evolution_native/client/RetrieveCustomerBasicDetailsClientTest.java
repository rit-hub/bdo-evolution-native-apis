package com.bdo.evolution_native.client;

import com.bdo.evolution_native.JsonReader;
import com.bdo.evolution_native.client.model.TokenResponse;
import com.bdo.evolution_native.client.model.retrivemodel.CustProfBasicInqRq;
import com.bdo.evolution_native.client.model.retrivemodel.CustProfBasicInqRs;
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

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@ExtendWith(MockitoExtension.class)
class RetrieveCustomerBasicDetailsClientTest {

    @Mock
    private TokenManagerClient tokenManagerClient;
    private MockWebServer mockWebServer;
    private RetrieveCustomerBasicDetailsClient client;
    private CustProfBasicInqRq custProfBasicInqRq;
    private CustProfBasicInqRs custProfBasicInqRs;

    /**
     * Sets .
     *
     * @throws IOException the io exception
     */
    @BeforeEach
    void setup() throws IOException {
        custProfBasicInqRq = (CustProfBasicInqRq) new JsonReader<>(CustProfBasicInqRq.class)
            .loadTestJson("retrive-CustomerBasic/CustProfBasicInqRq.json");

        custProfBasicInqRs = (CustProfBasicInqRs) new JsonReader<>(CustProfBasicInqRs.class)
            .loadTestJson("retrive-CustomerBasic/CustProfBasicInqRs.json");
        mockWebServer = new MockWebServer();
        mockWebServer.start(9082);
        client = new RetrieveCustomerBasicDetailsClient(mockWebServer.url
            ("http://localhost:8080/parties/custemp/add").toString(), new WebClientConfig(),
                tokenManagerClient);
    }

    /**
     * Test create collateral.
     *
     * @throws IOException the io exception
     */
    @Test
    void testCustomerBasicClient() throws IOException {
        custProfBasicInqRq.getCustId().setCustPermId("1234");
        when(tokenManagerClient.getAccessToken()).thenReturn(Mono.just("1e4a0c19-2467-4d6f-a6b9-c77653af6b42"));
        mockWebServer.enqueue(new MockResponse().setResponseCode(200)
            .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .setBody(new ObjectMapper().writeValueAsString(custProfBasicInqRs)));
        Mono<CustProfBasicInqRs> actualResponse = client.retriveCustomerClient(custProfBasicInqRq);
        actualResponse.subscribe(response -> assertEquals(custProfBasicInqRs, response)).dispose();
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

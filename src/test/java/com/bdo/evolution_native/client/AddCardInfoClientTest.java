package com.bdo.evolution_native.client;

import com.bdo.evolution_native.JsonReader;
import com.bdo.evolution_native.client.model.addcard.CardAcctAddRq;
import com.bdo.evolution_native.client.model.addcard.CardAcctAddRs;
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
class AddCardInfoClientTest {
    @Mock
    private TokenManagerClient tokenManagerClient;
    private MockWebServer mockWebServer;
    private AddCardInfoClient client;
    private CardAcctAddRq cardAcctAddRq;
    private CardAcctAddRs cardAcctAddRs;

    /**
     * Sets .
     *
     * @throws IOException the io exception
     */
    @BeforeEach
    void setup() throws IOException {
        cardAcctAddRq = (CardAcctAddRq) new JsonReader<>(CardAcctAddRq.class)
            .loadTestJson("Add-Card/CardAcctAddRq.json");

        cardAcctAddRs = (CardAcctAddRs) new JsonReader<>(CardAcctAddRs.class)
            .loadTestJson("Add-Card/CardAcctAddRs.json");
        mockWebServer = new MockWebServer();
        mockWebServer.start(9082);
        client = new AddCardInfoClient(mockWebServer.url
            ("http://localhost:8080/parties/custprofbasic/add").toString(), new WebClientConfig(),
            tokenManagerClient);
    }

    /**
     * Test create collateral.
     *
     * @throws IOException the io exception
     */
    @Test
    void testCardAddClient() throws IOException {
        when(tokenManagerClient.getAccessToken()).thenReturn(Mono.just("1e4a0c19-2467-4d6f-a6b9-c77653af6b42"));
        mockWebServer.enqueue(new MockResponse().setResponseCode(201)
            .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .setBody(new ObjectMapper().writeValueAsString(cardAcctAddRs)));
        Mono<CardAcctAddRs> actualResponse = client.addCardInfoClient(cardAcctAddRq);
        actualResponse.subscribe(response -> assertEquals(cardAcctAddRs, response)).dispose();
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

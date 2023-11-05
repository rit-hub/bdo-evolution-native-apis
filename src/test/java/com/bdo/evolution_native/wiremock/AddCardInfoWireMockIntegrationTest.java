package com.bdo.evolution_native.wiremock;

import com.bdo.evolution_native.JsonReader;
import com.bdo.evolution_native.client.model.addcard.CardAcctAddRq;
import com.bdo.evolution_native.client.model.addcard.CardAcctAddRs;
import com.bdo.evolution_native.client.model.custacctadd.AcctCustAddRq;
import com.bdo.evolution_native.client.model.custacctadd.AcctCustAddRs;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Wire mock integration test.
 */
@ExtendWith(MockitoExtension.class)
class AddCardInfoWireMockIntegrationTest {
    /**
     * The Server.
     */
    WireMockServer server;
    private CardAcctAddRs cardAcctAddRs;
    private CardAcctAddRq cardAcctAddRq;
    /**
     * Sets .
     */
    @BeforeEach
    void setup() throws IOException {
        JsonReader<CardAcctAddRq> mapper1 = new JsonReader<>(CardAcctAddRq.class);
        cardAcctAddRq = (CardAcctAddRq) mapper1.loadTestJson("Add-Card/CardAcctAddRq.json");
        JsonReader<CardAcctAddRs> mapper2 = new JsonReader<>(CardAcctAddRs.class);
        cardAcctAddRs = (CardAcctAddRs) mapper2.loadTestJson("Add-Card/CardAcctAddRs.json");
        server = new WireMockServer(WireMockConfiguration
            .wireMockConfig()
            .extensions(new ResponseTemplateTransformer(true))
            .port(8096));
        server.start();
        RestAssured.port = server.port();

    }

    /**
     * Cust list retrieve test post 200.
     */
    @Test
    void custAcctInitiateCallWiremockTest() {
        CardAcctAddRs msg = RestAssured.given()
            .header("Authorization", "BearerToken")
            .body(cardAcctAddRq)
            .post("/CreditCard/CardAccount/Initiate")
            .then().log().body(true)
            .assertThat()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .extract()
            .as(CardAcctAddRs.class);
        assertEquals(cardAcctAddRs, msg);
        server.stop();

    }
}

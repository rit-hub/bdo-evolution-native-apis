package com.bdo.evolution_native.wiremock;

import com.bdo.evolution_native.JsonReader;
import com.bdo.evolution_native.client.model.initiatemodel.CustProfBasicAddRq;
import com.bdo.evolution_native.client.model.initiatemodel.CustProfBasicAddRs;
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
class InitiateCustomerWireMockIntegrationTest {
    /**
     * The Server.
     */
    WireMockServer server;
    private CustProfBasicAddRs custProfBasicAddRs;
    private CustProfBasicAddRq custProfBasicAddRq;
    /**
     * Sets .
     */
    @BeforeEach
    void setup() throws IOException {
        JsonReader<CustProfBasicAddRq> mapper1 = new JsonReader<>(CustProfBasicAddRq.class);
        custProfBasicAddRq = (CustProfBasicAddRq) mapper1.loadTestJson("Initiate-CustomerBasic/CustProfBasicAddRq.json");
        JsonReader<CustProfBasicAddRs> mapper2 = new JsonReader<>(CustProfBasicAddRs.class);
        custProfBasicAddRs = (CustProfBasicAddRs) mapper2.loadTestJson("Initiate-CustomerBasic/CustProfBasicAddRs.json");
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
    void empDetailsInitiateCallWiremockTest() {
        CustProfBasicAddRs msg = RestAssured.given()
            .header("Authorization", "BearerToken")
            .body(custProfBasicAddRq)
            .post("/PartyReference/CustomerProfile/Initiate")
            .then().log().body(true)
            .assertThat()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .extract()
            .as(CustProfBasicAddRs.class);
        assertEquals(custProfBasicAddRs, msg);
        server.stop();

    }
}

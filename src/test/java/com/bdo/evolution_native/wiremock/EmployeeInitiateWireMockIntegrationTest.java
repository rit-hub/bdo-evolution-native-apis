package com.bdo.evolution_native.wiremock;

import com.bdo.evolution_native.JsonReader;
import com.bdo.evolution_native.client.model.employee.CustEmpAddRq;
import com.bdo.evolution_native.client.model.employee.CustEmpAddRs;
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
class EmployeeInitiateWireMockIntegrationTest {
    /**
     * The Server.
     */
    WireMockServer server;
    private CustEmpAddRs custEmpAddRs;
    private CustEmpAddRq custEmpAddRq;
    /**
     * Sets .
     */
    @BeforeEach
    void setup() throws IOException {
        JsonReader<CustEmpAddRq> mapper1 = new JsonReader<>(CustEmpAddRq.class);
        custEmpAddRq = (CustEmpAddRq) mapper1.loadTestJson("Employee-Add/CustEmpAddRq.json");
        JsonReader<CustEmpAddRs> mapper2 = new JsonReader<>(CustEmpAddRs.class);
        custEmpAddRs = (CustEmpAddRs) mapper2.loadTestJson("Employee-Add/CustEmpAddRs.json");
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
        CustEmpAddRs msg = RestAssured.given()
            .header("Authorization", "BearerToken")
            .body(custEmpAddRq)
            .post("/PartyReference/CustomerProfile/EmployeeDetails/Initiate")
            .then().log().body(true)
            .assertThat()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .extract()
            .as(CustEmpAddRs.class);
        assertEquals(custEmpAddRs, msg);
        server.stop();

    }
}

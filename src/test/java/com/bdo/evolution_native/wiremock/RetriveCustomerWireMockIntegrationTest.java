package com.bdo.evolution_native.wiremock;

import com.bdo.evolution_native.JsonReader;
import com.bdo.evolution_native.client.model.employee.CustEmpAddRq;
import com.bdo.evolution_native.client.model.employee.CustEmpAddRs;
import com.bdo.evolution_native.client.model.retrivemodel.CustProfBasicInqRq;
import com.bdo.evolution_native.client.model.retrivemodel.CustProfBasicInqRs;
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
class RetriveCustomerWireMockIntegrationTest {
    /**
     * The Server.
     */
    WireMockServer server;
    private CustProfBasicInqRq custProfBasicInqRq;

    private CustProfBasicInqRs custProfBasicInqRs;
    /**
     * Sets .
     */
    @BeforeEach
    void setup() throws IOException {
        JsonReader<CustProfBasicInqRq> mapper1 = new JsonReader<>(CustProfBasicInqRq.class);
        custProfBasicInqRq = (CustProfBasicInqRq) mapper1.loadTestJson("retrive-CustomerBasic/CustProfBasicInqRq.json");
        JsonReader<CustProfBasicInqRs> mapper2 = new JsonReader<>(CustProfBasicInqRs.class);
        custProfBasicInqRs = (CustProfBasicInqRs) mapper2.loadTestJson("retrive-CustomerBasic/CustProfBasicInqRs.json");
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
    void retriveCustomerCallWiremockTest() {
        CustProfBasicInqRs msg = RestAssured.given()
            .header("Authorization", "BearerToken")
            .body(custProfBasicInqRq)
            .post("/PartyReferenceDataDirectory/CustomerInformation/Retrieve")
            .then().log().body(true)
            .assertThat()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .extract()
            .as(CustProfBasicInqRs.class);
        assertEquals(custProfBasicInqRs, msg);
        server.stop();

    }
}

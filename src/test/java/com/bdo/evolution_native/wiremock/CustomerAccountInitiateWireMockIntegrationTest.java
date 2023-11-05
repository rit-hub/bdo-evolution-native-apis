package com.bdo.evolution_native.wiremock;

import com.bdo.evolution_native.JsonReader;
import com.bdo.evolution_native.client.model.custacctadd.AcctCustAddRq;
import com.bdo.evolution_native.client.model.custacctadd.AcctCustAddRs;
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
class CustomerAccountInitiateWireMockIntegrationTest {
    /**
     * The Server.
     */
    WireMockServer server;
    private AcctCustAddRs acctCustAddRs;
    private AcctCustAddRq acctCustAddRq;
    /**
     * Sets .
     */
    @BeforeEach
    void setup() throws IOException {
        JsonReader<AcctCustAddRq> mapper1 = new JsonReader<>(AcctCustAddRq.class);
        acctCustAddRq = (AcctCustAddRq) mapper1.loadTestJson("Customer-Account-Initiate/AcctCustAddRq.json");
        JsonReader<AcctCustAddRs> mapper2 = new JsonReader<>(AcctCustAddRs.class);
        acctCustAddRs = (AcctCustAddRs) mapper2.loadTestJson("Customer-Account-Initiate/AcctCustAddRs.json");
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
        AcctCustAddRs msg = RestAssured.given()
            .header("Authorization", "BearerToken")
            .body(acctCustAddRq)
            .post("/PartyReference/NewSavingsAccount/Initiate")
            .then().log().body(true)
            .assertThat()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .extract()
            .as(AcctCustAddRs.class);
        assertEquals(acctCustAddRs, msg);
        server.stop();

    }
}

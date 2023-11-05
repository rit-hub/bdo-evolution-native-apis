package com.bdo.evolution_native.wiremock;

import com.bdo.evolution_native.client.model.ErrorType;
import com.bdo.evolution_native.client.model.StatusType;
import com.bdo.evolution_native.client.model.customerlist.*;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * The type Wire mock integration test.
 */
@ExtendWith(MockitoExtension.class)
class WireMockIntegrationTest {
    /**
     * The Server.
     */
    WireMockServer server;
    private CustListInqRs custListInqRs;

    /**
     * Sets .
     */
    @BeforeEach
    void setup() {
        server = new WireMockServer(WireMockConfiguration
            .wireMockConfig()
            .extensions(new ResponseTemplateTransformer(true))
            .port(8098));
        server.start();
        RestAssured.port = server.port();

    }

    /**
     * Cust list retrieve test post 200.
     */
    @Test
    void custListRetrieveTestPost200() {
        custListInqRs = CustListInqRs.builder()
            .rqUID(RqUID.builder()
                .requestUid("3fa85f64-5717-4562-b3fc-2c963f66afa6")
                .build())
            .status(StatusType.builder()
                .statusCode(2)
                .statusDescription("string")
                .supportUid("3fa85f64-5717-4562-b3fc-2c963f66afa6")
                .supportDescription("string")
                .errorCount(0)
                .warningCount(0)
                .errors(Arrays.asList(ErrorType.builder()
                    .source("string")
                    .errorNumber("string")
                    .errorDescription("string")
                    .errorDetails("string")
                    .errField("string")
                    .errType("s")
                    .errorTagName("string")
                    .hostErrorCode("string")
                    .useCode("st")
                    .supportUid("3fa85f64-5717-4562-b3fc-2c963f66afa6")
                    .rows(Collections.singletonList(0))
                    .build()))
                .warnings(Collections.singletonList(ErrorType.builder()
                    .source("string")
                    .errorNumber("string")
                    .errorDescription("string")
                    .errorDetails("string")
                    .errField("string")
                    .errType("s")
                    .errorTagName("string")
                    .hostErrorCode("string")
                    .useCode("st")
                    .supportUid("3fa85f64-5717-4562-b3fc-2c963f66afa6")
                    .rows(Collections.singletonList(0))
                    .build()))
                .build())
            .recCtrlOut(RecCtrlOutType.builder()
                .matchedRec(0)
                .sentRec(0)
                .cursor("string")
                .build())
            .custBasics(Collections.singletonList(CustBasicDtlType.builder()
                .custId(CustIdType.builder()
                    .custPermId("string")
                    .custPermIdMasked("string")
                    .build())
                .custType("string")
                .custMailCustCode("string")
                .taxId(SSNIDType.builder()
                    .id("string")
                    .idMasked("")
                    .build())
                .nationalId(NINIDType.builder()
                    .id("string")
                    .idMasked("")
                    .build())
                .phoneNum(PhoneNumTypeWithExtension.builder()
                    .phoneType("string")
                    .phone("string")
                    .intlDialCode("string")
                    .phoneExt("string")
                    .build())
                .birthDt(BirthDateType.builder()
                    .date("2023-08-08")
                    .dateMasked("")
                    .build())
                .custAddr(NonParsedAddrType.builder()
                    .fullName("string")
                    .addr1("string")
                    .addr2("string")
                    .addr3("string")
                    .addr4("string")
                    .addr5("string")
                    .postalCode("string")
                    .zipCode("string")
                    .addrCode("string")
                    .houseName("string")
                    .houseNum("string")
                    .street("string")
                    .apartmentNum("string")
                    .district("string")
                    .city("string")
                    .stateProv("string")
                    .postLocaleCode("string")
                    .country("string")
                    .countryCode("string")
                    .county("string")
                    .build())
                .shortName("string")
                .custStatusCode("string")
                .taxInfoCode(TINIDType.builder()
                    .id("string")
                    .idMasked("")
                    .build())
                .accessId("str")
                .custDlrDlrGroupFlag("string")
                .bankId("string")
                .branchId("string")
                .emailAddress("string")
                .mobileNoUpdDt("string")
                .custAcctRelType("string")
                .emailAddUpdDt("string")
                .citizenCode("string")
                .residenceCode("string")
                .riskCode("string")
                .build()))
            .build();


        CustListInqRq custListInqRq = CustListInqRq.builder().debitCardNumber("1234").build();



        CustListInqRs msg = RestAssured.given()
            .header("Authorization", "BearerToken")
            .body(custListInqRq)
            .post("/PartyReference/CustomerProfileSummary/Retrieve")
            .then().log().body(true)
            .assertThat()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .extract()
            .as(CustListInqRs.class);
        assertEquals(custListInqRs, msg);
        server.stop();

    }
}

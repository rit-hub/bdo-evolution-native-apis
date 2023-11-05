package com.bdo.evolution_native.controller;

import com.bdo.evolution_native.JsonReader;
import com.bdo.evolution_native.model.initiatemodel.AddCustomerRequest;
import com.bdo.evolution_native.model.initiatemodel.AddCustomerResponse;
import com.bdo.evolution_native.service.impl.InitiateCustomerBasicDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@ExtendWith(MockitoExtension.class)
class InitiateCustomerBasicDetailsApiTest {
    @InjectMocks
    private InitiateCustomerBasicDetailsApi initiateCustomerBasicDetailsApi;

    @Mock
    private InitiateCustomerBasicDetailsServiceImpl service;
    private AddCustomerRequest msRequest;

    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() throws IOException {

        MockitoAnnotations.openMocks(this);
        msRequest = (AddCustomerRequest) new JsonReader<>(AddCustomerRequest.class).loadTestJson("Initiate-CustomerBasic/AddCustomerRequest.json");

    }

    /**
     * Test cust list retrieve get call.
     */
    @Test
    void testCustListRetrieveGetCall() {
        AddCustomerResponse response = new AddCustomerResponse();
        when(service.initiateCustomer(Mockito.any(), Mockito.any()))
            .thenReturn(Mono.just(response));
        ResponseEntity<AddCustomerResponse> apiResponse = initiateCustomerBasicDetailsApi.empDetailsInitiateCall(
                 null, null, null, null, null, msRequest)
            .block();
        //verify(customerAccountSearchService, times(1)).retriveCustomer(request);
        Assertions.assertEquals(HttpStatus.CREATED, apiResponse.getStatusCode());
    }
}

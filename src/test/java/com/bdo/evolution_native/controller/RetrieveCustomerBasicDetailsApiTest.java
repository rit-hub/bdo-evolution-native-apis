package com.bdo.evolution_native.controller;

import com.bdo.evolution_native.model.retrivemodel.RetrieveCustomerResponse;
import com.bdo.evolution_native.service.impl.RetrieveCustomerBasicDetailsServiceImpl;
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

import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@ExtendWith(MockitoExtension.class)
class RetrieveCustomerBasicDetailsApiTest {
    @InjectMocks
    private RetrieveCustomerBasicDetailsApi retrieveCustomerBasicDetailsApi;

    @Mock
    private RetrieveCustomerBasicDetailsServiceImpl service;

    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test cust list retrieve get call.
     */
    @Test
    void testCustListRetrieveGetCall() {
        RetrieveCustomerResponse response = new RetrieveCustomerResponse();
        when(service.retriveCustomer(Mockito.anyString(), Mockito.any()))
            .thenReturn(Mono.just(response));
        ResponseEntity<RetrieveCustomerResponse> apiResponse = retrieveCustomerBasicDetailsApi.customerProfileRetrieve(
                "1234",  null, null, null, null, null)
            .block();
        Assertions.assertEquals(HttpStatus.OK, apiResponse.getStatusCode());
    }
}

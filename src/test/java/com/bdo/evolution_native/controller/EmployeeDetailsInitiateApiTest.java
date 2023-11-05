package com.bdo.evolution_native.controller;

import com.bdo.evolution_native.model.employee.CustomerInquireRequest;
import com.bdo.evolution_native.model.employee.CustomerInquireResponse;
import com.bdo.evolution_native.service.impl.EmployeeDetailsInitiateServiceImpl;
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

/**
 * The type Customer account search api test.
 */
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@ExtendWith(MockitoExtension.class)
class EmployeeDetailsInitiateApiTest {
    @InjectMocks
    private EmployeeDetailsInitiateApi employeeDetailsInitiateApi;

    @Mock
    private EmployeeDetailsInitiateServiceImpl employeeDetailsInitiateService;

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
        CustomerInquireRequest request = new CustomerInquireRequest();
        CustomerInquireResponse response = new CustomerInquireResponse();
        when(employeeDetailsInitiateService.initiateEmployeeDetails(Mockito.any(),Mockito.any()))
            .thenReturn(Mono.just(response));
        ResponseEntity<CustomerInquireResponse> apiResponse = employeeDetailsInitiateApi.empDetailsInitiateCall(
                         null, null, null, null, null, request)
            .block();
        //verify(customerAccountSearchService, times(1)).retriveCustomer(request);
        Assertions.assertEquals(HttpStatus.CREATED, apiResponse.getStatusCode());
    }

}


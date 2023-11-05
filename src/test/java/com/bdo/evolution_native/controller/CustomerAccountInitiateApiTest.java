package com.bdo.evolution_native.controller;

import com.bdo.evolution_native.model.custacctadd.AccountRequest;
import com.bdo.evolution_native.model.custacctadd.AccountResponse;
import com.bdo.evolution_native.model.employee.CustomerInquireRequest;
import com.bdo.evolution_native.model.employee.CustomerInquireResponse;
import com.bdo.evolution_native.model.initiatemodel.AddCustomerRequest;
import com.bdo.evolution_native.model.initiatemodel.AddCustomerResponse;
import com.bdo.evolution_native.service.impl.CustomerAccountInitiateServiceImpl;
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
class CustomerAccountInitiateApiTest {
    @InjectMocks
    private CustomerAccountInitiateApi customerAccountInitiateApi;

    @Mock
    private CustomerAccountInitiateServiceImpl customerAccountInitiateService;
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
        AccountRequest request = new AccountRequest();
        AccountResponse response = new AccountResponse();
        when(customerAccountInitiateService.initiateCustomerAccount(Mockito.any(),Mockito.any()))
            .thenReturn(Mono.just(response));
        ResponseEntity<AccountResponse> apiResponse = customerAccountInitiateApi.custAcctInitiateCall(
                         null, null, null, null,  request)
            .block();
        //verify(customerAccountSearchService, times(1)).retriveCustomer(request);
        Assertions.assertEquals(HttpStatus.CREATED, apiResponse.getStatusCode());
    }

}


package com.bdo.evolution_native.controller;

import com.bdo.evolution_native.model.customerlist.CustomerSearchInquiryRequest;
import com.bdo.evolution_native.model.customerlist.CustomerSearchInquiryResponse;
import com.bdo.evolution_native.service.impl.CustomerAccountSearchServiceImpl;
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
class CustomerAccountSearchApiTest {
    @InjectMocks
    private CustomerAccountSearchApi customerAccountSearchApi;

    @Mock
    private CustomerAccountSearchServiceImpl customerAccountSearchService;

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
        CustomerSearchInquiryRequest request = new CustomerSearchInquiryRequest();
        request.setTaxId("1234");
        CustomerSearchInquiryResponse response = new CustomerSearchInquiryResponse();
        when(customerAccountSearchService.retriveCustomer(Mockito.any(),Mockito.any()))
            .thenReturn(Mono.just(response));
        ResponseEntity<CustomerSearchInquiryResponse> apiResponse = customerAccountSearchApi.custListRetrieveGetCall(
                request, null,null,null,null)
            .block();
        //verify(customerAccountSearchService, times(1)).retriveCustomer(request);
        Assertions.assertEquals(HttpStatus.OK, apiResponse.getStatusCode());
    }

}


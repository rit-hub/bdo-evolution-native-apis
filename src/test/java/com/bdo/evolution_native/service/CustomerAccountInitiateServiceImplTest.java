package com.bdo.evolution_native.service;

import com.bdo.evolution_native.JsonReader;
import com.bdo.evolution_native.client.CustomerAccountInitiateClient;
import com.bdo.evolution_native.client.EmployeeDetailsClient;
import com.bdo.evolution_native.client.model.custacctadd.AcctCustAddRs;
import com.bdo.evolution_native.client.model.employee.CustEmpAddRs;
import com.bdo.evolution_native.model.custacctadd.AccountRequest;
import com.bdo.evolution_native.model.custacctadd.AccountResponse;
import com.bdo.evolution_native.model.employee.CustomerInquireRequest;
import com.bdo.evolution_native.model.employee.CustomerInquireResponse;
import com.bdo.evolution_native.service.impl.CustomerAccountInitiateServiceImpl;
import com.bdo.evolution_native.service.impl.EmployeeDetailsInitiateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CustomerAccountInitiateServiceImplTest {
    @Mock
    private CustomerAccountInitiateClient customerAccountInitiateClient;

    @InjectMocks
    private CustomerAccountInitiateServiceImpl customerAccountInitiateService;

    private AcctCustAddRs response;
    private AccountRequest request;
    private ServletUriComponentsBuilder servletUriComponentsBuilder;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        JsonReader<AccountRequest> mapper1 = new JsonReader<>(AccountRequest.class);
        request = (AccountRequest) mapper1.loadTestJson("Customer-Account-Initiate/AccountRequest.json");
        JsonReader<AcctCustAddRs> mapper2 = new JsonReader<>(AcctCustAddRs.class);
        response = (AcctCustAddRs) mapper2.loadTestJson("Customer-Account-Initiate/AcctCustAddRs.json");
        servletUriComponentsBuilder = ServletUriComponentsBuilder.fromCurrentRequest();
    }

    @Test
    void testRetriveCustomer() {
        // Set the necessary fields in sorResponse
        when(customerAccountInitiateClient.customerClient(any())).thenReturn(Mono.just(response));

        // Call the method being tested
        Mono<AccountResponse> resultMono = customerAccountInitiateService.initiateCustomerAccount(request, servletUriComponentsBuilder);

        // Assert the result
        AccountResponse result = resultMono.block();
        assertNotNull(result);
    }


}

package com.bdo.evolution_native.service;

import com.bdo.evolution_native.JsonReader;
import com.bdo.evolution_native.client.EmployeeDetailsClient;
import com.bdo.evolution_native.client.model.employee.CustEmpAddRs;
import com.bdo.evolution_native.exception.RecordNotFoundException;
import com.bdo.evolution_native.model.employee.CustomerInquireRequest;
import com.bdo.evolution_native.model.employee.CustomerInquireResponse;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EmployeeDetailsInitiateServiceImplTest {
    @Mock
    private EmployeeDetailsClient employeeDetailsClient;

    @InjectMocks
    private EmployeeDetailsInitiateServiceImpl employeeDetailsInitiateService;

    private CustEmpAddRs response;
    private CustomerInquireRequest request;
    private ServletUriComponentsBuilder servletUriComponentsBuilder;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        JsonReader<CustomerInquireRequest> mapper1 = new JsonReader<>(CustomerInquireRequest.class);
        request = (CustomerInquireRequest) mapper1.loadTestJson("Employee-Add/CustomerInquireRequest.json");
        JsonReader<CustEmpAddRs> mapper2 = new JsonReader<>(CustEmpAddRs.class);
        response = (CustEmpAddRs) mapper2.loadTestJson("Employee-Add/CustEmpAddRs.json");
        servletUriComponentsBuilder = ServletUriComponentsBuilder.fromCurrentRequest();
    }

    @Test
    void testRetriveCustomer() {
        // Set the necessary fields in sorResponse
        when(employeeDetailsClient.employeeClient(any())).thenReturn(Mono.just(response));

        // Call the method being tested
        Mono<CustomerInquireResponse> resultMono = employeeDetailsInitiateService.initiateEmployeeDetails(request, servletUriComponentsBuilder);

        // Assert the result
        CustomerInquireResponse result = resultMono.block();
        assertNotNull(result);
    }
    @Test
    void testRetriveCustomerWithNullResponse() {
        response.setCustId(null);
        // Set the necessary fields in sorResponse
        when(employeeDetailsClient.employeeClient(any())).thenReturn(Mono.just(response));
        Mono<CustomerInquireResponse> resultMono = employeeDetailsInitiateService.initiateEmployeeDetails(request, servletUriComponentsBuilder);
        assertNotNull(resultMono);
    }

}

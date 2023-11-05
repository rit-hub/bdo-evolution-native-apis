package com.bdo.evolution_native.service;

import com.bdo.evolution_native.JsonReader;
import com.bdo.evolution_native.client.InitiateCustomerBasicDetailsClient;
import com.bdo.evolution_native.client.model.initiatemodel.CustProfBasicAddRs;
import com.bdo.evolution_native.model.initiatemodel.AddCustomerRequest;
import com.bdo.evolution_native.model.initiatemodel.AddCustomerResponse;
import com.bdo.evolution_native.service.impl.InitiateCustomerBasicDetailsServiceImpl;
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
class InitiateCustomerBasicDetailsServiceImplTest {
    @Mock
    private InitiateCustomerBasicDetailsClient client;

    @InjectMocks
    private InitiateCustomerBasicDetailsServiceImpl initiateCustomerBasicDetailsService;

    private CustProfBasicAddRs sorResponse;
    private AddCustomerRequest msRequest;
    private AddCustomerResponse msResponse;
    private ServletUriComponentsBuilder servletUriComponentsBuilder;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        msRequest = (AddCustomerRequest) new JsonReader<>(AddCustomerRequest.class).loadTestJson("Initiate-CustomerBasic/AddCustomerRequest.json");
        sorResponse = (CustProfBasicAddRs) new JsonReader<>(CustProfBasicAddRs.class).loadTestJson("Initiate-CustomerBasic/CustProfBasicAddRs.json");
        msResponse = (AddCustomerResponse) new JsonReader<>(AddCustomerResponse.class).loadTestJson("Initiate-CustomerBasic/AddCustomerResponse.json");
        servletUriComponentsBuilder = ServletUriComponentsBuilder.fromCurrentRequest();
    }

    /**
     * Test retrive customer.
     */
    @Test
    void testRetriveCustomer() {

        // Set the necessary fields in sorResponse
        when(client.initiateCustomerClient(any())).thenReturn(Mono.just(sorResponse));

        // Call the method being tested
        Mono<AddCustomerResponse> resultMono = initiateCustomerBasicDetailsService.initiateCustomer(msRequest, servletUriComponentsBuilder);

        // Assert the result
        AddCustomerResponse result = resultMono.block();
        assertNotNull(result);
    }

}

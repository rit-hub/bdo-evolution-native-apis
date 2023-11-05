package com.bdo.evolution_native.service;

import com.bdo.evolution_native.JsonReader;
import com.bdo.evolution_native.client.RetrieveCustomerBasicDetailsClient;
import com.bdo.evolution_native.client.model.retrivemodel.CustProfBasicInqRs;
import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.bdo.evolution_native.exception.RecordNotFoundException;
import com.bdo.evolution_native.model.retrivemodel.RetrieveCustomerResponse;
import com.bdo.evolution_native.service.impl.RetrieveCustomerBasicDetailsServiceImpl;
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
class RetrieveCustomerBasicDetailsServiceImplTest {
    @InjectMocks
    private RetrieveCustomerBasicDetailsServiceImpl service;

    @Mock
    private RetrieveCustomerBasicDetailsClient client;

    private CustProfBasicInqRs response;
    private ServletUriComponentsBuilder servletUriComponentsBuilder;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        JsonReader<CustProfBasicInqRs> mapper2 = new JsonReader<>(CustProfBasicInqRs.class);
        response = (CustProfBasicInqRs) mapper2.loadTestJson("retrive-CustomerBasic/CustProfBasicInqRs.json");
        servletUriComponentsBuilder = ServletUriComponentsBuilder.fromCurrentRequest();
    }

    @Test
    void testRetriveCustomerProBasic() {
        // Set the necessary fields in sorResponse
        when(client.retriveCustomerClient(any())).thenReturn(Mono.just(response));

        // Call the method being tested
        Mono<RetrieveCustomerResponse> resultMono = service.retriveCustomer("1234", servletUriComponentsBuilder);

        // Assert the result
        RetrieveCustomerResponse result = resultMono.block();
        assertNotNull(result);
    }

    //    @Test
//    void testRetriveCustomerProBasicWithNullResponse() {
//        // Set the necessary fields in sorResponse
//        when(client.retriveCustomerClient(any())).thenReturn(Mono.just(null));
//
//        // Call the method being tested
//        assertThrows(RecordNotFoundException.class, () -> {
//                service.retriveCustomer("1234", servletUriComponentsBuilder);
//            });
//    }
    @Test
    void testSetDefaultValuesIfNull() {
        // Call the method to set default values
        response.setCustProfBasic(null);
        response.setCustId(null);
        // Assertions to check if the default values are set correctly

        when(client.retriveCustomerClient(any())).thenReturn(Mono.just(response));

        // Call the method being tested
        Mono<RetrieveCustomerResponse> resultMono = service.retriveCustomer("1234", servletUriComponentsBuilder);

        assertNotNull(resultMono.block().getData().getCustomerProfileDetails());

    }
}

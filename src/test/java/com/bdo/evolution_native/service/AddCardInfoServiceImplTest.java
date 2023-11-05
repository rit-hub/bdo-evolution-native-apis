package com.bdo.evolution_native.service;

import com.bdo.evolution_native.JsonReader;
import com.bdo.evolution_native.client.AddCardInfoClient;
import com.bdo.evolution_native.client.model.addcard.CardAcctAddRs;
import com.bdo.evolution_native.model.addcard.CardRequest;
import com.bdo.evolution_native.model.addcard.CardResponse;
import com.bdo.evolution_native.service.impl.AddCardInfoServiceImpl;
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
class AddCardInfoServiceImplTest {
    @Mock
    private AddCardInfoClient client;

    @InjectMocks
    private AddCardInfoServiceImpl addCardInfoService;

    private CardAcctAddRs sorResponse;
    private CardRequest msRequest;
    private CardResponse msResponse;
    private ServletUriComponentsBuilder servletUriComponentsBuilder;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        msRequest = (CardRequest) new JsonReader<>(CardRequest.class).loadTestJson("Add-Card/CardRequest.json");
        sorResponse = (CardAcctAddRs) new JsonReader<>(CardAcctAddRs.class).loadTestJson("Add-Card/CardAcctAddRs.json");
        System.out.println(sorResponse);
        msResponse = (CardResponse) new JsonReader<>(CardResponse.class).loadTestJson("Add-Card/CardResponse.json");
        servletUriComponentsBuilder = ServletUriComponentsBuilder.fromCurrentRequest();
    }

    /**
     * Test retrive customer.
     */
    @Test
    void testAddCardService() {

        // Set the necessary fields in sorResponse
        when(client.addCardInfoClient(any())).thenReturn(Mono.just(sorResponse));

        // Call the method being tested
        Mono<CardResponse> resultMono = addCardInfoService.addCardInfoPost(msRequest, servletUriComponentsBuilder);

        // Assert the result
        CardResponse result = resultMono.block();
        assertNotNull(result);
    }
}

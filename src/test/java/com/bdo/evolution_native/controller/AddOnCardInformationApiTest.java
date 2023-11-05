package com.bdo.evolution_native.controller;

import com.bdo.evolution_native.model.addcard.CardRequest;
import com.bdo.evolution_native.model.addcard.CardResponse;
import com.bdo.evolution_native.service.impl.AddCardInfoServiceImpl;
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

class AddOnCardInformationApiTest {
    @InjectMocks
    private AddOnCardInformationApi addOnCardInformationApi;

    @Mock
    private AddCardInfoServiceImpl addCardInfoService;
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
    void testAddCardPostCall() {
        CardRequest request = new CardRequest();
        CardResponse response = new CardResponse();
        when(addCardInfoService.addCardInfoPost(Mockito.any(),Mockito.any()))
            .thenReturn(Mono.just(response));
        ResponseEntity<CardResponse> apiResponse = addOnCardInformationApi.creditCardCardAccountInitiatePostCall(
                null, null, null, null,"12345",request)
            .block();
        //verify(customerAccountSearchService, times(1)).retriveCustomer(request);
        Assertions.assertEquals(HttpStatus.CREATED, apiResponse.getStatusCode());
    }
}

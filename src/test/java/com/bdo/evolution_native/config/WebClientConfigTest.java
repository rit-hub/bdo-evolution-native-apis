package com.bdo.evolution_native.config;

import com.bdo.evolution_native.exception.ClientException;
import com.bdo.evolution_native.exception.ClientServerException;
import com.bdo.evolution_native.exception.SystemApiError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import reactor.util.retry.Retry;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Web client config test.
 */
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@ExtendWith(MockitoExtension.class)
class WebClientConfigTest {
    @InjectMocks
    private WebClientConfig webClientConfig;
    @Mock
    private Retry.RetrySignal retrySignal;

    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test handle errors with client exception.
     */
    @Test
    void testHandleErrorsWithClientException() {
        // Create a mock ClientException
        ClientException clientException = new ClientException(400, "Bad Request");

        // Create an instance of WebClientConfig
        WebClientConfig webClientConfig = new WebClientConfig();

        // Call the handleErrors method with the mock ClientException
        Throwable result = webClientConfig.handleErrors(clientException);

        // Verify that the result is the same as the mock ClientException
        assertSame(clientException, result);
    }

    /**
     * Test handle errors with client server exception.
     */
    @Test
    void testHandleErrorsWithClientServerException() {
        // Create a mock ClientServerException
        ClientServerException clientServerException = new ClientServerException(500, "Internal Server Error");

        // Create an instance of WebClientConfig
        WebClientConfig webClientConfig = new WebClientConfig();

        // Call the handleErrors method with the mock ClientServerException
        Throwable result = webClientConfig.handleErrors(clientServerException);

        // Verify that the result is the same as the mock ClientServerException
        assertSame(clientServerException, result);
    }

    /**
     * Test handle errors with unexpected error.
     */
    @Test
    void testHandleErrorsWithUnexpectedError() {
        // Create a mock Throwable for an unexpected error
        Throwable unexpectedError = new RuntimeException("Unexpected error");

        // Create an instance of WebClientConfig
        WebClientConfig webClientConfig = new WebClientConfig();

        // Call the handleErrors method with the mock unexpected error
        Throwable result = webClientConfig.handleErrors(unexpectedError);

        // Verify that the result is a SystemApiError and contains the original error
        assertTrue(result instanceof SystemApiError);
        assertEquals("Unexpected error occurs during processing SOR response", result.getMessage());
        assertSame(unexpectedError, result.getCause());
    }


}







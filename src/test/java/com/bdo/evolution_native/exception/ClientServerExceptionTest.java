package com.bdo.evolution_native.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Client server exception test.
 */
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@ExtendWith(MockitoExtension.class)
class ClientServerExceptionTest {

    /**
     * Test client server exception constructor.
     */
    @Test
    void testClientServerExceptionConstructor() {
        // Define test data
        Integer status = 500;
        String message = "Internal Server Error";

        // Create a ClientServerException instance
        ClientServerException exception = new ClientServerException(status, message);

        // Verify that the exception properties are correctly set
        assertEquals(status, exception.getStatus());
        assertEquals(message, exception.getMessage());
    }
}

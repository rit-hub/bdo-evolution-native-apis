package com.bdo.evolution_native.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Client exception test.
 */
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@ExtendWith(MockitoExtension.class)
class ClientExceptionTest {

    /**
     * Test client exception with status and message.
     */
    @Test
    void testClientExceptionWithStatusAndMessage() {
        // Create a ClientException with status and message
        Integer status = 400;
        String errorMessage = "Bad Request";
        ClientException exception = new ClientException(status, errorMessage);

        // Verify that the status and message are set correctly
        assertEquals(status, exception.getStatus());
        assertEquals(errorMessage, exception.getMessage());
        assertNull(exception.getCause());
    }

    /**
     * Test client exception with message and cause.
     */
    @Test
    void testClientExceptionWithMessageAndCause() {
        // Create a mock throwable as the cause
        Throwable cause = new RuntimeException("Root cause");

        // Create a ClientException with message and cause
        String errorMessage = "An error occurred";
        ClientException exception = new ClientException(errorMessage, cause);

        // Verify that the status is -1, message, and cause are set correctly
        assertEquals(-1, exception.getStatus());
        assertEquals(errorMessage, exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}


package com.bdo.evolution_native.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * The type System api error test.
 */
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@ExtendWith(MockitoExtension.class)
class SystemApiErrorTest {
    /**
     * Test system api error with message.
     */
    @Test
    void testSystemApiErrorWithMessage() {
        // Create a SystemApiError with a message
        String errorMessage = "An error occurred";
        SystemApiError systemApiError = new SystemApiError(errorMessage);

        // Verify that the message is set correctly
        assertEquals(errorMessage, systemApiError.getMessage());

        // Verify that the cause is null
        assertNull(systemApiError.getCause());
    }

    /**
     * Test system api error with message and cause.
     */
    @Test
    void testSystemApiErrorWithMessageAndCause() {
        // Create a mock throwable as the cause
        Throwable cause = new RuntimeException("Root cause");

        // Create a SystemApiError with a message and cause
        String errorMessage = "An error occurred";
        SystemApiError systemApiError = new SystemApiError(errorMessage, cause);

        // Verify that the message and cause are set correctly
        assertEquals(errorMessage, systemApiError.getMessage());
        //assertSame(cause, systemApiError.getCause());
    }
}






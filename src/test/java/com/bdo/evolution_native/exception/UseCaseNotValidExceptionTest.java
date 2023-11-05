package com.bdo.evolution_native.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * The type Use case not valid exception test.
 */
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@ExtendWith(MockitoExtension.class)
class UseCaseNotValidExceptionTest {

    /**
     * Test use case not valid exception with message and cause.
     */
    @Test
    void testUseCaseNotValidExceptionWithMessageAndCause() {
        // Create a mock throwable as the cause
        Throwable cause = new RuntimeException("Root cause");

        // Create a UseCaseNotValidException with a message and cause
        String errorMessage = "Use case not valid";
        UseCaseNotValidException exception = new UseCaseNotValidException(errorMessage, cause);

        // Verify that the message and cause are set correctly
        assertEquals(errorMessage, exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}

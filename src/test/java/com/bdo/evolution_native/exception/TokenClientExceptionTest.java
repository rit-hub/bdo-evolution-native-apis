package com.bdo.evolution_native.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TokenClientExceptionTest {

    @Test
    void testConstructorWithStatusAndMessage() {
        Integer status = 400;
        String message = "Bad Request";

        TokenClientException exception = new TokenClientException(status, message);

        assertEquals(status, exception.getStatus());
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndThrowable() {
        String message = "Error Message";
        Throwable cause = new RuntimeException("Root Cause");

        TokenClientException exception = new TokenClientException(message, cause);

        assertEquals(-1, exception.getStatus()); // Status should default to -1
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithNullMessageAndThrowable() {
        Throwable cause = new RuntimeException("Root Cause");

        TokenClientException exception = new TokenClientException(null, cause);

        assertEquals(-1, exception.getStatus()); // Status should default to -1
        assertNull(exception.getMessage()); // Message should be null
        assertEquals(cause, exception.getCause());
    }
}

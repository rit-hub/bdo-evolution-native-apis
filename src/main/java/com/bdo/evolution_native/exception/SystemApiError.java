package com.bdo.evolution_native.exception;
/**
 * This is for System Api Error
 */
public class SystemApiError extends RuntimeException {
    public SystemApiError(final String message) {
        super(message);
    }

    public SystemApiError(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}

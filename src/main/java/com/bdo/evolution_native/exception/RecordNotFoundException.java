package com.bdo.evolution_native.exception;

/**
 * The type Record not found exception.
 */
public class RecordNotFoundException extends RuntimeException {
    /**
     * Instantiates a new Record not found exception.
     *
     */
    public RecordNotFoundException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}

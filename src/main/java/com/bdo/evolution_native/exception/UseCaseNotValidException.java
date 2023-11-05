package com.bdo.evolution_native.exception;

/**
 * The type Use case not valid exception.
 */
public class UseCaseNotValidException extends RuntimeException {
    /**
     * Instantiates a new Use case not valid exception.
     *
     */
    public UseCaseNotValidException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}

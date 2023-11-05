package com.bdo.evolution_native.exception;

import lombok.Data;

/**
 * This is for Client Exception by Authorization API
 */
@Data
public class TokenClientException extends RuntimeException {

    private final Integer status;

    private final String message;

    public TokenClientException(final Integer status, final String message) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public TokenClientException(final String message, final Throwable throwable) {
        super(message, throwable);
        this.message = message;
        this.status = -1;
    }

}

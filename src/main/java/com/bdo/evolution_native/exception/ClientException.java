package com.bdo.evolution_native.exception;

import lombok.Data;

/**
 * This is for Client Exception
 */
@Data
public class ClientException extends RuntimeException {

    private final Integer status;

    private final String message;

    public ClientException(final Integer status, final String message) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public ClientException(final String message, final Throwable throwable) {
        super(message, throwable);
        this.message = message;
        this.status = -1;
    }

}

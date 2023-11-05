package com.bdo.evolution_native.exception;

import lombok.Data;

/**
 * This is for Client Server Exception
 */
@Data
public class ClientServerException extends RuntimeException {

    private final Integer status;
    private final String message;

    public ClientServerException(final Integer status, final String message) {
        super(message);
        this.message = message;
        this.status = status;
    }
}

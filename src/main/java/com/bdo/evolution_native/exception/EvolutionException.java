package com.bdo.evolution_native.exception;

import com.bdo.evolution_native.client.model.StatusType;
import com.bdo.evolution_native.constants.EvolutionConstantUtils;

/**
 * This is for Evolution Exception
 */
public class EvolutionException extends RuntimeException {

    private final transient StatusType status;

    public EvolutionException(final StatusType status) {
        super(EvolutionConstantUtils.EVOLUTION_FAILED_STATUS);
        this.status = new StatusType(status.getStatusCode(), status.getStatusDescription(), status.getSupportUid(),
                status.getSupportDescription(), status.getErrorCount(), status.getWarningCount(),
                status.getErrors(), status.getWarnings());
    }

    public StatusType getStatus() {
        return new StatusType(status.getStatusCode(), status.getStatusDescription(), status.getSupportUid(),
                status.getSupportDescription(), status.getErrorCount(), status.getWarningCount(),
                status.getErrors(), status.getWarnings());
    }
}

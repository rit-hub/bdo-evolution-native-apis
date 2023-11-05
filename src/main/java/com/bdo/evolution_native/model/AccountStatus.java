package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * A characteristic of account which refers to the LifecycleStatus of
 * account like opened, closed, blocked, etc.  Specifies the current state of an account.
 * (ISO20022)
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class AccountStatus {
    @JsonProperty("AccountStatus")
    private Status status = new Status();

    @JsonProperty("AccountStatusType")
    private String accountStatusType;

}

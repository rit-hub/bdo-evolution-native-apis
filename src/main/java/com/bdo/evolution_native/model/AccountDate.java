package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * A characteristic of account which refers to LifecycleDate of account (e.g., ClosingDate, OpeningDate, LiveDate, etc.)
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class AccountDate {
    @JsonProperty("AccountDateType")
    private String accountDateType;

    @JsonProperty("AccountDate")
    private DateTime dateTime = new DateTime();

}

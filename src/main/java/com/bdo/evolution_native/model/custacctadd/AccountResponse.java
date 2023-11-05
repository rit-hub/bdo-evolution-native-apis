package com.bdo.evolution_native.model.custacctadd;

import com.bdo.evolution_native.model.Links;
import com.bdo.evolution_native.model.Meta;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * AccountResponse
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class AccountResponse {
    @JsonProperty("Data")
    private AccountResponsePayload data;

    @JsonProperty("Links")
    private Links links;

    @JsonProperty("Meta")
    private Meta meta;
}

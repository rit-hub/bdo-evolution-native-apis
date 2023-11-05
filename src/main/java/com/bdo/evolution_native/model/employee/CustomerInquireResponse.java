package com.bdo.evolution_native.model.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CustomerInquireResponse
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustomerInquireResponse {
    @JsonProperty("Data")
    private CustomerInquireResponseData data;

    @JsonProperty("Links")
    private Links links;

    @JsonProperty("Meta")
    private Meta meta;
}

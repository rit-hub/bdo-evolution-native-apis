package com.bdo.evolution_native.model.customerlist;

import com.bdo.evolution_native.model.Links;
import com.bdo.evolution_native.model.Meta;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * The type Customer search inquiry response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustomerSearchInquiryResponse {
    @JsonProperty("Data")
    private CustomerSearchInquiryResponsePayload data;

    @JsonProperty("Links")
    private Links links;

    @JsonProperty("Meta")
    private Meta meta;
}

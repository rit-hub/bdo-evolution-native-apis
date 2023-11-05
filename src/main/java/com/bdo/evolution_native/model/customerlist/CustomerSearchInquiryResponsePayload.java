package com.bdo.evolution_native.model.customerlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * The type Customer search inquiry response payload.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustomerSearchInquiryResponsePayload {
    @JsonProperty("Reference")
    private Reference reference;
}

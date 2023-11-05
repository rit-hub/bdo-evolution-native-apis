package com.bdo.evolution_native.model.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;

/**
 * CustomerInquireRequest
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustomerInquireRequest {
    @JsonProperty("CustomerEmploymentDetails")
    @Valid
    private CustomerEmploymentDetails customerEmploymentDetails;
}

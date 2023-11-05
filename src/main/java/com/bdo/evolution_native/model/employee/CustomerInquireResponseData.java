package com.bdo.evolution_native.model.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CustomerInquireResponseData
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustomerInquireResponseData {
    @JsonProperty("RequestId")
    private String requestId;

    @JsonProperty("CardAccountDetails")
    private CustomerInquireResponseDataCardAccountDetails cardAccountDetails;

    @JsonProperty("CustomerId")
    private CustomerInquireResponseDataCustomerId customerId;
}

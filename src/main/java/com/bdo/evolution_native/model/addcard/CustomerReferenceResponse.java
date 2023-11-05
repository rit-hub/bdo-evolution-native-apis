package com.bdo.evolution_native.model.addcard;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CustomerReferenceResponse
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerReferenceResponse {
    @JsonProperty("RequestId")
    private String requestId;

    @JsonProperty("AccountDetails")
    private AccountDetailsResponse accountDetails;

    @JsonProperty("CustomerIdRequest")
    private CustomerIdResponse customerId;

    @JsonProperty("ShortName")
    private String shortName;

}

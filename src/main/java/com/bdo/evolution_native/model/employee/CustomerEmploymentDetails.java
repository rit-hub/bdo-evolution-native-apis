package com.bdo.evolution_native.model.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;

/**
 * CustomerEmploymentDetails
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustomerEmploymentDetails {
    @JsonProperty("RequestId")
    private String requestId;

    @JsonProperty("CardAccountDetails")
    @Valid
    private CardAccountDetails cardAccountDetails;

    @JsonProperty("CustomerProfileBasic")
    @Valid
    private CustomerProfileBasic customerProfileBasic;

    @JsonProperty("EmploymentDetails")
    @Valid
    private EmploymentDetails employmentDetails;

}

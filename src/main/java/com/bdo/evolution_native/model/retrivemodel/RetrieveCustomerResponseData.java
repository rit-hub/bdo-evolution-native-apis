package com.bdo.evolution_native.model.retrivemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * RetrieveCustomerResponseData
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RetrieveCustomerResponseData {
    @JsonProperty("CustomerProfileDetails")
    private CustomerProfileDetails customerProfileDetails;
  
}

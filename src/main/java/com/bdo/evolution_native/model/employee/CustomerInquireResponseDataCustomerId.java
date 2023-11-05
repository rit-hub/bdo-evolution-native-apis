package com.bdo.evolution_native.model.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CustomerInquireResponseDataCustomerId
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustomerInquireResponseDataCustomerId {
    @JsonProperty("CustomerNumber")
    private String customerNumber;

    @JsonProperty("CustomerNumberMasked")
    private String customerNumberMasked;

}

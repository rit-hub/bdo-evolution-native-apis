package com.bdo.evolution_native.model.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;

/**
 * CustomerProfileBasic
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustomerProfileBasic {
    @JsonProperty("CustomerId")
    @Valid
    private CustomerId customerId;
}

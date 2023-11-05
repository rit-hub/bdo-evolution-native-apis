package com.bdo.evolution_native.model.employee;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Size;

/**
 * CustomerId
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustomerId {
    @Size(max = 10, message = EvolutionConstantUtils.CUSTOMER_NUMBER_SIZE_VALIDATION)
    @JsonProperty("CustomerNumber")
    private String customerNumber;

}

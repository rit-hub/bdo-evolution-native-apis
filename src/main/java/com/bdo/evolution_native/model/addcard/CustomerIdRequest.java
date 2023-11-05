package com.bdo.evolution_native.model.addcard;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * CustomerIdRequest
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@Valid
public class CustomerIdRequest {
    @NotBlank(message = EvolutionConstantUtils.CUSTOMER_NUMBER_NOT_BLANK)
    @Size(max = 10, message = EvolutionConstantUtils.CUSTOMER_NUMBER_SIZE_VALIDATION)
    @JsonProperty("CustomerNumber")
    private String customerNumber;

    @JsonProperty("CustomerNumberMasked")
    @Size(max = 10, message = EvolutionConstantUtils.CUSTOMER_NUMBER_MASKED_SIZE_VALIDATION)
    private String customerNumberMasked;

}

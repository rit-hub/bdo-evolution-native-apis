package com.bdo.evolution_native.model.custacctadd;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * CustomerId
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class CustomerId {
    @Valid
    @NotBlank(message = EvolutionConstantUtils.CUSTOMER_NUMBER_NOT_BLANK)
    @Size(max = 10, message = EvolutionConstantUtils.CUSTOMER_NUMBER_SIZE_VALIDATION)
    @JsonProperty("CustomerNumber")
    private String customerNumber;

    @JsonProperty("CustomerNumberMasked")
    private String customerNumberMasked;

}

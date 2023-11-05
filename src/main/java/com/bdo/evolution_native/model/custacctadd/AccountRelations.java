package com.bdo.evolution_native.model.custacctadd;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * AccountRelations
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class AccountRelations {
    @Valid
    @JsonProperty("CustomerId")
    private CustomerId customerId;

    @Valid
    @NotBlank(message = EvolutionConstantUtils.RELATION_CODE_NOT_BLANK)
    @Size(max = 3, message = EvolutionConstantUtils.RELATION_CODE_SIZE_MESSAGE)
    @JsonProperty("RelationCode")
    private String relationCode;

}

package com.bdo.evolution_native.model.employee;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Size;

/**
 * CardAccountDetails
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CardAccountDetails {
    @Size(max = 12, message = EvolutionConstantUtils.ACCID_SIZE_VALIDATION)
    @JsonProperty("AccountId")
    private String accountId;

    @JsonProperty("AccountType")
    private String accountType;

}

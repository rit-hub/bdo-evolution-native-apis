package com.bdo.evolution_native.model.custacctadd;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Size;

/**
 * AliasBankAcctId
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class AliasBankAcctId {
    @Size(max = 2, message = EvolutionConstantUtils.ACCOUNT_TYPE_SIZE_MESSAGE)
    @JsonProperty("AccountType")
    private String accountType;

}

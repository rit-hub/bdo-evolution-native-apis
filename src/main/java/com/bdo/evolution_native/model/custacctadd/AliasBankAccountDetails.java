package com.bdo.evolution_native.model.custacctadd;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Size;


/**
 * AliasBankAccountDetails
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class AliasBankAccountDetails {
    @Size(max = 35, message = EvolutionConstantUtils.ALIAS_ACCOUNT_ID_SIZE_MESSAGE)
    @JsonProperty("AliasAccountId")
    private String aliasAccountId;
    @Size(max = 35, message = EvolutionConstantUtils.ALIAS_ACCOUNT_ID_MASKED_SIZE_MESSAGE)
    @JsonProperty("AliasAccountIdMasked")
    private String aliasAccountIdMasked;
}

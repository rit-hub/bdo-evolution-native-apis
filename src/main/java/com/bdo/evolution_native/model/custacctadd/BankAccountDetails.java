package com.bdo.evolution_native.model.custacctadd;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.bdo.evolution_native.enums.AccountType;
import com.bdo.evolution_native.util.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * BankAccountDetails
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class BankAccountDetails {
    @Valid
    @NotBlank(message = EvolutionConstantUtils.ACCOUNT_TYPE_NOT_BLANK)
    @Size(max = 2, message = EvolutionConstantUtils.ACCOUNT_TYPE_SIZE_MESSAGE)
    @ValueOfEnum(enumClass = AccountType.class, message = EvolutionConstantUtils.ACCOUNT_TYPE_FORMAT_MESSAGE_ADDCARD)
    @JsonProperty("AccountType")
    private String accountType;

    @Valid
    @NotBlank(message = EvolutionConstantUtils.ACCOUNT_ID_NOT_BLANK)
    @Size(max = 12, message = EvolutionConstantUtils.BANK_ACCOUNT_ID_SIZE_MESSAGE)
    @JsonProperty("AccountId")
    private String accountId;
    @Size(max = 12, message = EvolutionConstantUtils.BANK_ACCOUNT_ID_MASKED_SIZE_MESSAGE)
    @JsonProperty("AccountIdMasked")
    private String accountIdMasked;

}

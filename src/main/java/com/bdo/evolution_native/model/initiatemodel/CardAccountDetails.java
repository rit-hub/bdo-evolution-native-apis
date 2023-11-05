package com.bdo.evolution_native.model.initiatemodel;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.bdo.evolution_native.enums.AccountTypeInitiate;
import com.bdo.evolution_native.util.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * CardAccountDetails
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@Valid
public class CardAccountDetails {

    @JsonProperty("AccountId")
    @Size(max = 19, message = EvolutionConstantUtils.ACCOUNT_ID_SIZE_MESSAGE)
    private String accountId;

    @JsonProperty("AccountType")
    @ValueOfEnum(enumClass = AccountTypeInitiate.class, message = EvolutionConstantUtils.ACCOUNT_TYPE_FORMAT_MESSAGE)
    private String accountType;

    @JsonProperty("AccountTitle")
    private String accountTitle;

    @JsonProperty("CardHolderFlag")
    @Size(max = 1, message = EvolutionConstantUtils.CARD_HOLDER_FLAG_SIZE_MESSAGE)
    private String cardHolderFlag;

    @JsonProperty("CardIssuance")
    private String cardIssuance;

}

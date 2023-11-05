package com.bdo.evolution_native.model.addcard;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * CardAccountdetails
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@Valid
public class CardAccountdetails {
    @JsonProperty("AccountId")
    @Size(max = 10 , message = EvolutionConstantUtils.ACCOUNT_ID_SIZE_MESSAGE)
    private String accountId;

    @JsonProperty("AccountIdMasked")
    private String accountIdMasked;

    @JsonProperty("AccountType")
    @Size(max = 2, message = EvolutionConstantUtils.ACCOUNT_TYPE_SIZE_MESSAGE)
    private String accountType;

    @JsonProperty("ShortName")
    @Size(max = 18, message = EvolutionConstantUtils.SHORT_NAME_SIZE_MESSAGE)
    private String shortName;

    @JsonProperty("AccountLinkCount")
    private Integer accountLinkCount;

    @NotBlank(message = EvolutionConstantUtils.ISOID_MANDATORY_VALIDATION)
    @Pattern(regexp = "^-?\\d{1,6}$", message = EvolutionConstantUtils.ISOID_NUMBER_VALIDATION)
    @JsonProperty("ISOId")
    private String isOId;

    @JsonProperty("VipClass")
    @Pattern(regexp = "^-?\\d{1,1}$", message = EvolutionConstantUtils.VIP_CLASS_SIZE_VALIDATION)
    private String vipClass;

}

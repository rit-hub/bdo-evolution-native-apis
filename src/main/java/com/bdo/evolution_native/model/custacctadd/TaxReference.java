package com.bdo.evolution_native.model.custacctadd;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * TaxReference
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class TaxReference {

    @DecimalMax(value = "7")
    @Digits(integer = 7, fraction = 4, message = "Tax liability amount must match the pattern NUM(7,4)")
    @JsonProperty("TaxLiabilityPercentageAmount")
    private BigDecimal taxLiabilityPercentageAmount;
    @Size(max = 1, message = EvolutionConstantUtils.NOTICE_FLAG_DESC_SIZE_VALIDATION)
    @JsonProperty("NoticeFlagDescription")
    private String noticeFlagDescription;

}

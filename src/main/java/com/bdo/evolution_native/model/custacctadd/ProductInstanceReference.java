package com.bdo.evolution_native.model.custacctadd;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * ProductInstanceReference
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductInstanceReference {
    @JsonProperty("SavingsAccountNumber")
    private String savingsAccountNumber;

}

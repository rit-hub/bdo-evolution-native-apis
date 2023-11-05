package com.bdo.evolution_native.model.custacctadd;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;

/**
 * AccountRequest
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class AccountRequest {
    @Valid
    @JsonProperty("SavingAccountFacility")
    private SavingAccountFacilityRequest savingAccountFacility;

}

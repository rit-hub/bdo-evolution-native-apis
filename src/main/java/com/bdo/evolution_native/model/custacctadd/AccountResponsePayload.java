package com.bdo.evolution_native.model.custacctadd;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * AccountResponsePayload
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponsePayload {
    @JsonProperty("RequestUID")
    private String requestUID;

    @JsonProperty("SavingAccountFacility")
    private SavingAccountFacilityResponse savingAccountFacility;

}

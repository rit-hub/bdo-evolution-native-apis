package com.bdo.evolution_native.model.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CustomerInquireResponseDataCardAccountDetails
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustomerInquireResponseDataCardAccountDetails {
    @JsonProperty("AccountId")
    private String accountId;

    @JsonProperty("AccountIdMasked")
    private String accountIdMasked;

    @JsonProperty("AccountType")
    private String accountType;

}

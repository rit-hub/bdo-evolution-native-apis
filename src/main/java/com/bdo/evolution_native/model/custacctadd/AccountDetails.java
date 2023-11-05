package com.bdo.evolution_native.model.custacctadd;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;

/**
 * AccountDetails
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class AccountDetails {
    @Valid
    @JsonProperty("AliasBankAccountId")
    private AliasBankAcctId aliasBankAccountId;
    @Valid
    @JsonProperty("AliasBankAccountDetails")
    private AliasBankAccountDetails aliasBankAccountDetails;

    @Valid
    @JsonProperty("BankAccountDetails")
    private BankAccountDetails bankAccountDetails;

    @JsonProperty("AccountType")
    private String accountType;

    @JsonProperty("AccountCurrency")
    private String accountCurrency;
}

package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Definition of the associations to the account
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Account {
    @JsonProperty("AccountStatus")
    private AccountStatus accountStatus = new AccountStatus();

    @JsonProperty("AccountIdentification")
    private AccountIdentification accountIdentification = new AccountIdentification();

    @JsonProperty("AccountDate")
    private AccountDate accountDate = new AccountDate();

    @JsonProperty("AccountType")
    private AccountTypes accountType = new AccountTypes();

    @JsonProperty("AccountPurpose")
    private Description accountPurpose = new Description();

    @JsonProperty("AccountBalance")
    private List<AccountBalances> accountBalance;

    @JsonProperty("AccountCurrency")
    private AccountCurrency accountCurrency = new AccountCurrency();

    @JsonProperty("AccountDescription")
    private Description accountDescription = new Description();

}

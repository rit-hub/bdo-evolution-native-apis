package com.bdo.evolution_native.model.loan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * The state of the obligations managed on the account, the result of all entries on the account.  Numerical representation of the net increases and decreases in an account at a specific point in time. (ISO20022)  Banking: Amount available in an account for withdrawal or use. Read more: http://www.businessdictionary.com/definition/balance.html  Bookkeeping: Difference between the debit and credit sides of an account. Read more: http://www.businessdictionary.com/definition/balance.html
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Generated
public class Accountbalances {
    @JsonProperty("AccountBalance")
    private Amount accountBalance;

    @JsonProperty("AccountBalanceType")
    private String accountBalanceType;

    @JsonProperty("AccounBalanceDate")
    private Datetime accounBalanceDate = new Datetime();

}

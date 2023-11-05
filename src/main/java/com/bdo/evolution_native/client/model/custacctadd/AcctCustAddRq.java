package com.bdo.evolution_native.client.model.custacctadd;

import java.util.List;
import com.bdo.evolution_native.client.model.RqUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
/**
 * AcctCustAddRq
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class AcctCustAddRq {
  @JsonProperty("rqUID")
  private RqUID rqUID = null;

  @JsonProperty("aliasBankAcctId")
  private AliasBankAcctIdType aliasBankAcctId = null;

  @JsonProperty("bankAcctId")
  private BankAcctIdType bankAcctId = null;

  @JsonProperty("acctRelations")
  private List<AcctRelationTypeTaxLiability> acctRelations = null;
}

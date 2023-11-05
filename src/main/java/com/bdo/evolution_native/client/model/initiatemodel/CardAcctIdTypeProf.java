package com.bdo.evolution_native.client.model.initiatemodel;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CardAcctIdTypeProf
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CardAcctIdTypeProf {
  @JsonProperty("acctId")
  private String acctId;

  @JsonProperty("acctType")
  private String acctType;

  @JsonProperty("bankInfo")
  private BankInfoType bankInfo;

  @JsonProperty("cardHolderFlag")
  private String cardHolderFlag;

  @JsonProperty("ccMotoAcctType")
  private CCMotoAcctType ccMotoAcctType;
}
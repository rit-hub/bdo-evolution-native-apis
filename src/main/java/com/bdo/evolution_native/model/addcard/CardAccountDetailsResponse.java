package com.bdo.evolution_native.model.addcard;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CardAccountDetailsResponse
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardAccountDetailsResponse {
  @JsonProperty("CardAccountId")
  private String cardAccountId;

  @JsonProperty("AccountNumberMasked")
  private String accountNumberMasked;

  @JsonProperty("AccountType")
  private String accountType;

  @JsonProperty("BankId")
  private String bankId;

  @JsonProperty("ISOId")
  private String isOId;

  @JsonProperty("MemberId")
  private String memberId;

  @JsonProperty("CardTypeCode")
  private String cardTypeCode;

  @JsonProperty("CardFacility")
  private Object cardFacility;

  @JsonProperty("ProductInstanceReference")
  private Object productInstanceReference;

}

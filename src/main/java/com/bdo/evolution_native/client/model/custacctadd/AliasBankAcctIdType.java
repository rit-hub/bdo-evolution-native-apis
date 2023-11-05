package com.bdo.evolution_native.client.model.custacctadd;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * AliasBankAcctIdType
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class AliasBankAcctIdType {
  @JsonProperty("aliasAcctType")
  private String aliasAcctType = null;

  @JsonProperty("aliasAcctId")
  private String aliasAcctId = null;

  @JsonProperty("aliasAcctIdMasked")
  private String aliasAcctIdMasked = null;
}

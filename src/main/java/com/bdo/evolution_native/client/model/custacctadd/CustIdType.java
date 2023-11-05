package com.bdo.evolution_native.client.model.custacctadd;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
/**
 * CustIdType
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class CustIdType {
  @JsonProperty("custPermId")
  private String custPermId = null;

  @JsonProperty("custPermIdMasked")
  private String custPermIdMasked = null;
}

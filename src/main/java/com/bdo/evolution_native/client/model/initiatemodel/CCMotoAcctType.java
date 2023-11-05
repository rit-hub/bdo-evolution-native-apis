package com.bdo.evolution_native.client.model.initiatemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CCMotoAcctType
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CCMotoAcctType {
  @JsonProperty("expDt")
  private String expDt;
}
package com.bdo.evolution_native.client.model.initiatemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * SSNIDType
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class SSNIDType {
  @JsonProperty("id")
  private String id;

  @JsonProperty("idMasked")
  private String idMasked;
}
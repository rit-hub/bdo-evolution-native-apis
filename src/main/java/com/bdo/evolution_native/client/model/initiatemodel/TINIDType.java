package com.bdo.evolution_native.client.model.initiatemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * TINIDType
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class TINIDType {
  @JsonProperty("id")
  private String id;

  @JsonProperty("idMasked")
  private String idMasked;
}
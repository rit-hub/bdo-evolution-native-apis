package com.bdo.evolution_native.client.model.initiatemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * NINIDType
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class NINIDType {
  @JsonProperty("id")
  private String id;

  @JsonProperty("idMasked")
  private String idMasked;
}
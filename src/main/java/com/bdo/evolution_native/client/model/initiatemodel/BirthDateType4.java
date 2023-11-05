package com.bdo.evolution_native.client.model.initiatemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * BirthDateType4
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class BirthDateType4 {
  @JsonProperty("date")
  private String date;

  @JsonProperty("dateMasked")
  private String dateMasked;
}
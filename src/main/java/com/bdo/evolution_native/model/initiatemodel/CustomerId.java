package com.bdo.evolution_native.model.initiatemodel;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Size;

/**
 * CustomerId
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustomerId {
  @JsonProperty("CustomerNumber")
  @Size(max = 10, message = EvolutionConstantUtils.CUSTOMER_NUMBER_SIZE_MESSAGE)
  private String customerNumber = null;

}

package com.bdo.evolution_native.client.model.initiatemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * BankInfoType
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class BankInfoType {
  @JsonProperty("bankId")
  private String bankId;

  @JsonProperty("branchId")
  private String branchId;

  @JsonProperty("branchName")
  private String branchName;

  @JsonProperty("name")
  private String name;
}
package com.bdo.evolution_native.model.initiatemodel;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * CustomerProfileBasic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@Valid
public class CustomerProfileBasic {
  @JsonProperty("CustomerId")
  private @Valid CustomerId customerId;

  @JsonProperty("BranchId")
  @Size(max = 10 , message = EvolutionConstantUtils.BRANCHID_SIZE)
  private String branchId;

  @JsonProperty("BranchName")
  private String branchName;

  @JsonProperty("ShortName")
  @Size(max = 18, message = EvolutionConstantUtils.SHORT_NAME_SIZE_MESSAGE)
  private String shortName;

  @JsonProperty("DerivedShortName")
  @Size(max = 10, message = EvolutionConstantUtils.DERIVED_SHORT_NAME_SIZE_MESSAGE)
  private String derivedShortName;

  @JsonProperty("PrimaryOfficerCode")
  private String primaryOfficerCode;

  @JsonProperty("SecondaryOfficerCode")
  private String secondaryOfficerCode;

  @JsonProperty("ExtraOfficerCode1")
  private String extraOfficerCode1;

  @JsonProperty("ExtraOfficerCode2")
  private String extraOfficerCode2;

  @JsonProperty("OpenDate")
  @Pattern(regexp = EvolutionConstantUtils.REGEX_FOR_DATE, message = EvolutionConstantUtils.DATE_FORMAT_MESSAGE)
  private String openDate;

  @JsonProperty("InquiryLevelCode")
  @Size(max = 1, message = EvolutionConstantUtils.INQUIRY_LEVEL_CODE_SIZE_MESSAGE)
  private String inquiryLevelCode;

  @JsonProperty("MaintenanceLevelCode")
  @Size(max = 1, message = EvolutionConstantUtils.MAINTENANCE_LEVEL_CODE_SIZE_MESSAGE)
  private String maintenanceLevelCode;

  @JsonProperty("CustomerLocaleCode")
  private String customerLocaleCode;

  @JsonProperty("LastContactDate")
  @Pattern(regexp = EvolutionConstantUtils.REGEX_FOR_DATE, message = EvolutionConstantUtils.DATE_FORMAT_MESSAGE)
  private String lastContactDate;

  @JsonProperty("LastMaintenanceDate")
  @Pattern(regexp = EvolutionConstantUtils.REGEX_FOR_DATE, message = EvolutionConstantUtils.DATE_FORMAT_MESSAGE)
  private String lastMaintenanceDate;

  @JsonProperty("CustomerStatusCode")
  private String customerStatusCode;

  @JsonProperty("OwnHomeCode")
  @Size(max = 1, message = EvolutionConstantUtils.OWN_HOME_CODE_SIZE_MESSAGE)
  private String ownHomeCode;

}

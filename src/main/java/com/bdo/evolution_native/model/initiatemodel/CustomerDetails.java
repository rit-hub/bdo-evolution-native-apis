package com.bdo.evolution_native.model.initiatemodel;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * CustomerDetails
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@Valid
public class CustomerDetails {
  @JsonProperty("BirthDetails")
  private @Valid BirthDetails birthDetails;

  @JsonProperty("NationalDetails")
  private @Valid NationalDetails nationalDetails;

  @JsonProperty("SpouseDetails")
  private @Valid SpouseDetails spouseDetails;

  @JsonProperty("NumberOfDependents")
  private Integer numberOfDependents;

  @JsonProperty("ContactName")
  @Size(max = 30, message = EvolutionConstantUtils.CONTACT_NAME_SIZE_MESSAGE)
  private String contactName;

  @JsonProperty("ContactTitle")
  private String contactTitle;

  @JsonProperty("FathersName")
  private String fathersName;

  @JsonProperty("MothersName")
  private String mothersName;

  @JsonProperty("Language")
  private String language;

  @JsonProperty("CitizenCode")
  private String citizenCode;

  @JsonProperty("ResidenceCode")
  private String residenceCode;

  @JsonProperty("WithholdingCode")
  private String withholdingCode;

  @JsonProperty("WithholdingPercent")
  private BigDecimal withholdingPercent;

  @JsonProperty("PassportId")
  @Size(max = 10, message = EvolutionConstantUtils.PASSPORT_ID_SIZE_MESSAGE)
  private String passportId;

  @JsonProperty("CountryOfBirth")
  private String countryOfBirth;

  @JsonProperty("SolicitableCode")
  private String solicitableCode;

  @JsonProperty("AccommodationCode")
  private String accommodationCode;

  @JsonProperty("SocioEconomicCode")
  private String socioEconomicCode;

  @JsonProperty("Salutation")
  @Size(max = 20, message = EvolutionConstantUtils.SALUTATION_SIZE_MESSAGE)
  private String salutation;

  @JsonProperty("PreferredCustomerCode")
  private String preferredCustomerCode;

  @JsonProperty("MarketSegmentCode")
  private String marketSegmentCode;

  @JsonProperty("MaritalStatusCode")
  private String maritalStatusCode;

  @JsonProperty("SendMailCode")
  private String sendMailCode;

  @JsonProperty("MoveInDate")
  @Pattern(regexp = EvolutionConstantUtils.REGEX_FOR_DATE, message = EvolutionConstantUtils.DATE_FORMAT_MESSAGE)
  private String moveInDate;

  @JsonProperty("DateOfDeath")
  @Pattern(regexp = EvolutionConstantUtils.REGEX_FOR_DATE, message = EvolutionConstantUtils.DATE_FORMAT_MESSAGE)
  private String dateOfDeath;

  @JsonProperty("CustomerDocumentCode")
  @Size(max = 1, message = EvolutionConstantUtils.CUSTOMER_DOCUMENT_CODE_SIZE_MESSAGE)
  private String customerDocumentCode;

  @JsonProperty("CustomerDocumentActivityDate")
  @Pattern(regexp = EvolutionConstantUtils.REGEX_FOR_DATE, message = EvolutionConstantUtils.DATE_FORMAT_MESSAGE)
  private String customerDocumentActivityDate;

  @JsonProperty("CustomerMailAccountCode")
  private String customerMailAccountCode;

  @JsonProperty("CustomerMailCustomerCode")
  private String customerMailCustomerCode;

  @JsonProperty("CustomerDealerGroupFlag")
  @Size(max = 1, message = EvolutionConstantUtils.CUSTOEMR_DEALER_GROUP_FLAG_SIZE_MESSAGE)
  private String customerDealerGroupFlag;

  @JsonProperty("CustomerClassCode")
  private String customerClassCode;

  @JsonProperty("CustomerType")
  private String customerType;

  @JsonProperty("CustomerProspectCode")
  private String customerProspectCode;

  @JsonProperty("CustomerCollectionStatus")
  @Size(max = 1, message = EvolutionConstantUtils.CUSTOMER_COLLECTION_STATUS_SIZE_MESSAGE)
  private String customerCollectionStatus;

}

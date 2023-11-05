package com.bdo.evolution_native.model.retrivemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

/**
 * CustomerDetails
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDetails {
    @JsonProperty("BirthDetails")
    private BirthDetails birthDetails;

    @JsonProperty("NationalDetails")
    private NationalDetails nationalDetails;

    @JsonProperty("SpouseDetails")
    private SpouseDetails spouseDetails;

    @JsonProperty("NumberOfDependents")
    private Integer numberOfDependents;

    @JsonProperty("ContactName")
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
    private String moveInDate;

    @JsonProperty("DateOfDeath")
    private String dateOfDeath;

    @JsonProperty("CustomerDocumentCode")
    private String customerDocumentCode;

    @JsonProperty("CustomerDocumentActivityDate")
    private String customerDocumentActivityDate;

    @JsonProperty("CustomerMailAccountCode")
    private String customerMailAccountCode;

    @JsonProperty("CustomerMailCustomerCode")
    private String customerMailCustomerCode;

    @JsonProperty("CustomerDealerGroupFlag")
    private String customerDealerGroupFlag;

    @JsonProperty("CustomerClassCode")
    private String customerClassCode;

    @JsonProperty("CustomerType")
    private String customerType;

    @JsonProperty("CustomerProspectCode")
    private String customerProspectCode;

    @JsonProperty("CustomerCollectionStatus")
    private String customerCollectionStatus;

}

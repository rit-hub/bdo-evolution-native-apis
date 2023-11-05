package com.bdo.evolution_native.model.customerlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * The type Reference.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class Reference {
    @JsonProperty("CustomerPermanentId")
    private String customerPermanentId;

    @JsonProperty("CustomerType")
    private String customerType;

    @JsonProperty("CustomerMailCustomerCode")
    private String customerMailCustomerCode;

    @JsonProperty("CustomerStatusCode")
    private String customerStatusCode;

    @JsonProperty("CustAccountRelationshipType")
    private String custAccountRelationshipType;

    @JsonProperty("CustomerName")
    private String customerName;

    @JsonProperty("DateOfBirth")
    private String dateOfBirth;

    @JsonProperty("CitizenCode")
    private String citizenCode;

    @JsonProperty("ResidenceCode")
    private String residenceCode;

    @JsonProperty("RiskCode")
    private String riskCode;

    @JsonProperty("CustomerAddress")
    private CustomerAddress customerAddress;

    @JsonProperty("NationalId")
    private String nationalId;

    @JsonProperty("TaxInformationCode")
    private String taxInformationCode;

    @JsonProperty("TaxId")
    private String taxId;
    @JsonProperty("BranchId")
    private String branchId;
}

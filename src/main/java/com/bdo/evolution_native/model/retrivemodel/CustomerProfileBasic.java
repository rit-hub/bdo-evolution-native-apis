package com.bdo.evolution_native.model.retrivemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CustomerProfileBasic
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerProfileBasic {
    @JsonProperty("CustomerId")
    private CustomerId customerId;

    @JsonProperty("BranchId")
    private String branchId;

    @JsonProperty("BranchName")
    private String branchName;

    @JsonProperty("ShortName")
    private String shortName;

    @JsonProperty("DerivedShortName")
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
    private String openDate;

    @JsonProperty("InquiryLevelCode")
    private String inquiryLevelCode;

    @JsonProperty("MaintenanceLevelCode")
    private String maintenanceLevelCode;

    @JsonProperty("CustomerLocaleCode")
    private String customerLocaleCode;

    @JsonProperty("LastContactDate")
    private String lastContactDate;

    @JsonProperty("LastMaintenanceDate")
    private String lastMaintenanceDate;

    @JsonProperty("CustomerStatusCode")
    private String customerStatusCode;

    @JsonProperty("OwnHomeCode")
    private String ownHomeCode;

}

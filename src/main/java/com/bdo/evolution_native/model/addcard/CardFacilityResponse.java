package com.bdo.evolution_native.model.addcard;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CardFacilityResponse
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardFacilityResponse {
    @JsonProperty("AccountDetails")
    private AccountDetailsResponse accountDetails;

    @JsonProperty("EntitlementOptionDefinition")
    private String entitlementOptionDefinition;

    @JsonProperty("EntitlementOptionSetting")
    private String entitlementOptionSetting;

    @JsonProperty("RestrictionOptionDefinition")
    private String restrictionOptionDefinition;

    @JsonProperty("RestrictionOptionSetting")
    private String restrictionOptionSetting;

    @JsonProperty("ConfigurationOrOptions")
    private String configurationOrOptions;

    @JsonProperty("CardFacilityOptionDefinition")
    private String cardFacilityOptionDefinition;

    @JsonProperty("Associations")
    private String associations;

    @JsonProperty("PositionLimits")
    private String positionLimits;

    @JsonProperty("PositionLimitValue")
    private String positionLimitValue;

    @JsonProperty("DateType")
    private String dateType;

    @JsonProperty("StatementsSchedule")
    private String statementsSchedule;

    @JsonProperty("StatementReport")
    private String statementReport;

}

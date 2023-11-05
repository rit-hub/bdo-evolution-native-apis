package com.bdo.evolution_native.model.custacctadd;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * SavingAccountFacilityResponse
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SavingAccountFacilityResponse {
    @JsonProperty("ProductInstanceReference")
    private ProductInstanceReference productInstanceReference;

    @JsonProperty("AccountCurrency")
    private String accountCurrency;

    @JsonProperty("EntitlementOptionDefinition")
    private String entitlementOptionDefinition;

    @JsonProperty("EntitlementOptionSetting")
    private String entitlementOptionSetting;

    @JsonProperty("RestrictionOptionDefinition")
    private String restrictionOptionDefinition;

    @JsonProperty("RestrictionOptionSetting")
    private String restrictionOptionSetting;

    @JsonProperty("PositionLimitType")
    private String positionLimitType;

    @JsonProperty("PositionLimitSettings")
    private String positionLimitSettings;

    @JsonProperty("PositionLimitValue")
    private String positionLimitValue;

    @JsonProperty("DateType")
    private String dateType;

}

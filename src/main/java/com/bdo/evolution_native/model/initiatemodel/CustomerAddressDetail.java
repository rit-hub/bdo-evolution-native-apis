package com.bdo.evolution_native.model.initiatemodel;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * CustomerAddressDetail
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@Valid
public class CustomerAddressDetail {
    @JsonProperty("FullName")
    private String fullName;

    @JsonProperty("Line1")
    @Size(max = 10, message = EvolutionConstantUtils.LINE1_SIZE_MESSAGE)
    private String line1;

    @JsonProperty("Line2")
    @Size(max = 60, message = EvolutionConstantUtils.LINE2_SIZE_MESSAGE)
    private String line2;

    @JsonProperty("Line3")
    @Size(max = 35, message = EvolutionConstantUtils.LINE3_SIZE_MESSAGE)
    private String line3;

    @JsonProperty("Line4")
    @Size(max = 35, message = EvolutionConstantUtils.LINE4_SIZE_MESSAGE)
    private String line4;

    @JsonProperty("Region")
    @Size(max = 3, message = EvolutionConstantUtils.REGION_SIZE_MESSAGE)
    private String region;

    @JsonProperty("Province")
    @Size(max = 15, message = EvolutionConstantUtils.PROVINCE_SIZE_MESSAGE)
    private String province;

    @JsonProperty("PostalCode")
    private String postalCode;

    @JsonProperty("Country")
    private String country;
}
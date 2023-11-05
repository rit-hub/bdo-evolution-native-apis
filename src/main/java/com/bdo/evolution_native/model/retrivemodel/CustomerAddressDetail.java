package com.bdo.evolution_native.model.retrivemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CustomerAddressDetail
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerAddressDetail {
    @JsonProperty("FullName")
    private String fullName;

    @JsonProperty("Line1")
    private String line1;

    @JsonProperty("Line2")
    private String line2;

    @JsonProperty("Line3")
    private String line3;

    @JsonProperty("Line4")
    private String line4;

    @JsonProperty("Region")
    private String region;

    @JsonProperty("Province")
    private String province;

    @JsonProperty("PostalCode")
    private String postalCode;

    @JsonProperty("Country")
    private String country;

}

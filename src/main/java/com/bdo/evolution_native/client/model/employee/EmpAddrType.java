package com.bdo.evolution_native.client.model.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * EmpAddrType
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class EmpAddrType {
    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("addr1")
    private String addr1;

    @JsonProperty("addr2")
    private String addr2;

    @JsonProperty("addr3")
    private String addr3;

    @JsonProperty("addr4")
    private String addr4;

    @JsonProperty("addr5")
    private String addr5;

    @JsonProperty("postalCode")
    private String postalCode;

    @JsonProperty("zipCode")
    private String zipCode;

    @JsonProperty("zipSuffix")
    private String zipSuffix;

    @JsonProperty("zipRouteNum")
    private String zipRouteNum;

    @JsonProperty("zipChkDigit")
    private String zipChkDigit;

    @JsonProperty("postLocaleCode")
    private String postLocaleCode;

    @JsonProperty("addrCode")
    private String addrCode;
}

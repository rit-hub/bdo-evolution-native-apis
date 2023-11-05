package com.bdo.evolution_native.model.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * EmployeeAddressDetail
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class EmployeeAddressDetail {
    @JsonProperty("AddressCode")
    private String addressCode;

    @JsonProperty("HouseNumber")
    private String houseNumber;

    @JsonProperty("Street")
    private String street;

    @JsonProperty("District")
    private String district;

    @JsonProperty("City")
    private String city;

    @JsonProperty("StateProvince")
    private String stateProvince;

    @JsonProperty("CountryName")
    private String countryName;

    @JsonProperty("ZipCode")
    private String zipCode;

}

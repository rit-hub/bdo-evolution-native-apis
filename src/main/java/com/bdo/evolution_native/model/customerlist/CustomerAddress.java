package com.bdo.evolution_native.model.customerlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * The type Customer address.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustomerAddress {
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

    @JsonProperty("State")
    private String state;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("ZipCode")
    private String zipCode;
}


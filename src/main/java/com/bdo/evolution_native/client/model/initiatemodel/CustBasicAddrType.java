package com.bdo.evolution_native.client.model.initiatemodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CustBasicAddrType
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustBasicAddrType {
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

  @JsonProperty("houseName")
  private String houseName;

  @JsonProperty("houseNum")
  private String houseNum;

  @JsonProperty("street")
  private String street;

  @JsonProperty("apartmentNum")
  private String apartmentNum;

  @JsonProperty("district")
  private String district;

  @JsonProperty("city")
  private String city;

  @JsonProperty("stateProv")
  private String stateProv;

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

  @JsonProperty("drivenStatusCode")
  private String drivenStatusCode;

  @JsonProperty("country")
  private String country;

  @JsonProperty("countryCode")
  private String countryCode;

  @JsonProperty("county")
  private String county;

  @JsonProperty("addrCode")
  private String addrCode;
}
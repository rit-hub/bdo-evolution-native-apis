/*
 * Party Reference Customer Profile Retrieve API
 * The party reference data directory service domain maintains a potentially wide range of party reference data that might be used in any interaction between the bank and the party including relationship development, sales/marketing, servicing and product delivery. This can include general reference and contact details, party associations, demographic details and some servicing preferences. Different information may be maintained for different party types such as individuals, corporates, partners
 *
 * OpenAPI spec version: 0.0.1
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.bdo.evolution_native.model.retrivemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CardAccountDetails
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardAccountDetails {
    @JsonProperty("AccountId")
    private String accountId;

    @JsonProperty("AccountType")
    private String accountType;

    @JsonProperty("CardHolderFlag")
    private String cardHolderFlag;

    @JsonProperty("AccountTitle")
    private String accountTitle;

    @JsonProperty("CardIssuance")
    private String cardIssuance;

}

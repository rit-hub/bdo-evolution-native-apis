/*
 * Add on card information attached to account.
 * The Card Account Information API is used to access cards information and transactions.
 *
 * OpenAPI spec version: 0.0.1
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.bdo.evolution_native.model.addcard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;

/**
 * CardRequest
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CardRequest {
    @JsonProperty("CardFacility")
    private @Valid CardFacility cardFacility;

}

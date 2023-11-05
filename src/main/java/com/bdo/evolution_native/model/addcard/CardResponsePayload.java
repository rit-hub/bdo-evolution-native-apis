package com.bdo.evolution_native.model.addcard;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CardResponsePayload
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardResponsePayload {
    @JsonProperty("CustomerReference")
    private CustomerReferenceResponse customerReference;

    @JsonProperty("CardFacility")
    private CardFacilityResponse cardFacility;

}

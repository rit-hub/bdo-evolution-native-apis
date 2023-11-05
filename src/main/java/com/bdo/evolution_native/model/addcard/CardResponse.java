package com.bdo.evolution_native.model.addcard;

import com.bdo.evolution_native.model.Links;
import com.bdo.evolution_native.model.Meta;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


/**
 * CardResponse
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardResponse {
    @JsonProperty("Data")
    private CardResponsePayload data;

    @JsonProperty("Links")
    private Links links;

    @JsonProperty("Meta")
    private Meta meta;

}

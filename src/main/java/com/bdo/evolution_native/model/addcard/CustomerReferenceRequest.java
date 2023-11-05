package com.bdo.evolution_native.model.addcard;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * CustomerReferenceRequest
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustomerReferenceRequest {
    @JsonProperty("RequestId")
    private String requestId;

    @JsonProperty("CustomerId")
    private @Valid CustomerIdRequest customerId;

    @JsonProperty("LanguageCode")
    @Size(max = 1, message = EvolutionConstantUtils.LANGUAGE_CODE_SIZE_VALIDATION)
    private String languageCode;

}

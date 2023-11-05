package com.bdo.evolution_native.model.retrivemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * PhoneNumber
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneNumber {
    @JsonProperty("PhoneType")
    private String phoneType;

    @JsonProperty("Phone")
    private String phone;

    @JsonProperty("InitialDialCode")
    private String initialDialCode;

    @JsonProperty("Extension")
    private String extension;

}

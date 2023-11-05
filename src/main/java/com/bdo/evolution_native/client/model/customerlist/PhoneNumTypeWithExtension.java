package com.bdo.evolution_native.client.model.customerlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * The type Phone num type with extension.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class PhoneNumTypeWithExtension {
    @JsonProperty("phoneType")
    private String phoneType;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("intlDialCode")
    private String intlDialCode;

    @JsonProperty("phoneExt")
    private String phoneExt;

}

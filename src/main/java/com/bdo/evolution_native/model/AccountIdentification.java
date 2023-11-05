package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * The associated account number in any suitable format (e.g. IBAN)
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class AccountIdentification {
    @JsonProperty("AccountIdentificationType")
    private String accountIdentificationType;

    @JsonProperty("AccountIdentification")
    private Identifier identifier = new Identifier();

}

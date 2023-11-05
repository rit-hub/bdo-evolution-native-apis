package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * The type of current account (e.g. checking, student, small business)
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class AccountTypes {
    @JsonProperty("AccountType")
    private String accountType;

    @JsonProperty("AccountTypeName")
    private FieldName accountTypeFieldName = new FieldName();

}

package com.bdo.evolution_native.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * DepartmentAccountId
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class DepartmentAccountId {
    @JsonProperty("acctId")
    private String acctId;

    @JsonProperty("acctType")
    private String acctType;

}

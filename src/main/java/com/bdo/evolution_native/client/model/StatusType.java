package com.bdo.evolution_native.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * StatusType
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class StatusType {
    @JsonProperty("statusCode")
    private Integer statusCode;

    @JsonProperty("statusDesc")
    private String statusDescription;

    @JsonProperty("supportUID")
    private String supportUid;

    @JsonProperty("supportDescription")
    private String supportDescription;

    @JsonProperty("errorCount")
    private Integer errorCount;

    @JsonProperty("warningCount")
    private Integer warningCount;

    @JsonProperty("errors")
    private List<ErrorType> errors;

    @JsonProperty("warnings")
    private List<ErrorType> warnings;

}

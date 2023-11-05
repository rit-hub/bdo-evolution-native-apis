package com.bdo.evolution_native.client.model.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * StatusType
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class StatusType {
    @JsonProperty("statusCode")
    private Integer statusCode;

    @JsonProperty("statusDesc")
    private String statusDesc;

    @JsonProperty("supportUID")
    private String supportUID;

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

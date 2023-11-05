package com.bdo.evolution_native.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * ErrorType
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class ErrorType {
    @JsonProperty("source")
    private String source;

    @JsonProperty("errNum")
    private String errorNumber;

    @JsonProperty("errDesc")
    private String errorDescription;

    @JsonProperty("errDtl")
    private String errorDetails;

    @JsonProperty("errField")
    private String errField;

    @JsonProperty("errType")
    private String errType;

    @JsonProperty("errTagName")
    private String errorTagName;

    @JsonProperty("hostErrCode")
    private String hostErrorCode;

    @JsonProperty("useCode")
    private String useCode;

    @JsonProperty("supportUID")
    private String supportUid;

    @JsonProperty("rows")
    private List<Integer> rows;

}

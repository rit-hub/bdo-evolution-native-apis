package com.bdo.evolution_native.client.model.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * ErrorType
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class ErrorType {
    @JsonProperty("source")
    private String source;

    @JsonProperty("errNum")
    private String errNum;

    @JsonProperty("errDesc")
    private String errDesc;

    @JsonProperty("errDtl")
    private String errDtl;

    @JsonProperty("errField")
    private String errField;

    @JsonProperty("errType")
    private String errType;

    @JsonProperty("errTagName")
    private String errTagName;

    @JsonProperty("hostErrCode")
    private String hostErrCode;

    @JsonProperty("useCode")
    private String useCode;

    @JsonProperty("supportUID")
    private String supportUID;

    @JsonProperty("rows")
    private List<Integer> rows;


}

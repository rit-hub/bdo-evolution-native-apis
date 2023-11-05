package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Error {

    @JsonProperty("ErrorCode")
    @NotNull(message = "{ErrorCode.field.missing}")
    private String errorCode;

    @Size(min = 1, max = 500, message = "{Message.invalid.field.size}")
    @JsonProperty("Message")
    private String message;

    @Size(min = 1, max = 500, message = "{Path.invalid.field.size}")
    @JsonProperty("Path")
    private String path;

    @JsonProperty("Url")
    private String url;

}

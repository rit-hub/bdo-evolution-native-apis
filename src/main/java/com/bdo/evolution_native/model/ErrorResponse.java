package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Generated;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Generated
public class ErrorResponse {

    @JsonProperty("Code")
    @Size(min = 1, max = 40, message = "{Code.invalid.field.size}")
    private String code;

    @JsonProperty("Id")
    @Size(min = 1, max = 40, message = "{Id.invalid.field.size}")
    private String id;

    @JsonProperty("Message")
    @Size(min = 1, max = 500, message = "{Message.invalid.field.size}")
    private String message;

    @JsonProperty("Errors")
    @Size(min = 1, max = 500, message = "{Errors.invalid.field.size}")
    private List<Error> errors = new ArrayList<>();

    public void addError(final Error error) {
        if (Objects.isNull(this.errors)) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
    }

}

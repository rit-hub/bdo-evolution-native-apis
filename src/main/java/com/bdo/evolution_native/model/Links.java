package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Links {

    @JsonProperty("Self")
    @NotNull
    private String self;

    @JsonProperty("First")
    private String first;

    @JsonProperty("Prev")
    private String prev;

    @JsonProperty("Next")
    private String next;

    @JsonProperty("Last")
    private String last;

}

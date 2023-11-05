package com.bdo.evolution_native.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class Meta {

    @JsonProperty("TotalPages")
    private Integer totalPages;

    @JsonProperty("FirstAvailableDateTime")
    private String firstAvailableDateTime;

    @JsonProperty("LastAvailableDateTime")
    private String lastAvailableDateTime;

}

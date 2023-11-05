package com.bdo.evolution_native.model.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Meta Data relevant to the payload
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class Meta {
    @JsonProperty("TotalPages")
    private Integer totalPages;

    @JsonProperty("FirstAvailableDateTime")
    private String firstAvailableDateTime;

    @JsonProperty("LastAvailableDateTime")
    private String lastAvailableDateTime;
}

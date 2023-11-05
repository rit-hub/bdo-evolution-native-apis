package com.bdo.evolution_native.client.model.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * TokenRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
@Builder
public class TokenRequest {

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("grant_type")
    private String grantType;

    private String username;

    private String password;
}

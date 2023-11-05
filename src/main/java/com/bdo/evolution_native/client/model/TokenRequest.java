package com.bdo.evolution_native.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

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

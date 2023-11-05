package com.bdo.evolution_native.client;

import com.bdo.evolution_native.client.model.AccessTokenResponse;
import com.bdo.evolution_native.config.TokenClientConfig;
import com.bdo.evolution_native.exception.SystemApiError;
import com.bdo.evolution_native.util.MethodLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static com.bdo.evolution_native.constants.EvolutionConstantUtils.*;

@Component
public class TokenManagerClient {
    @Autowired
    private TokenClientConfig tokenClientConfig;
    @Value("${EVOLUTION.TOKEN.PATH}")
    private String tokenPath;
    @Value("${EVOLUTION.TOKEN.CHANNEL_ID_HEADER}")
    private String channelIdHeader;
    @Value("${EVOLUTION.TOKEN.SOURCE_HEADER}")
    private String sourceHeader;

    /**
     * This method is used to Check the Access Token
     *
     * @return String
     */
    @MethodLogger
    public Mono<String> getAccessToken() {
        return getAccessTokenResponse()
                .map(accessTokenResponse -> Optional.ofNullable(accessTokenResponse)
                        .map(AccessTokenResponse::getAccessToken)
                        .filter(token -> !token.isEmpty())
                        .orElseThrow(() -> new SystemApiError(EMPTY_OAUTH_RESPONSE)));
    }

    @MethodLogger
    private Mono<AccessTokenResponse> getAccessTokenResponse() {
        return tokenClientConfig.getOauthWebClient().get()
            .uri(tokenPath)
            .accept(MediaType.APPLICATION_JSON)
            .header(CHANNEL_ID_HEADER, channelIdHeader)
            .header(SOURCE_HEADER, sourceHeader)
            .retrieve()
            .bodyToMono(AccessTokenResponse.class)
            .retryWhen(tokenClientConfig.retryForOauthServerError())
            .retryWhen(tokenClientConfig.retryForOauthConnectionError())
            .onErrorMap(tokenClientConfig::oAuthHandleErrors)
            .switchIfEmpty(Mono.error(new SystemApiError(EMPTY_OAUTH_RESPONSE)));
    }
}

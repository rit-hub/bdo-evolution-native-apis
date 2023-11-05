package com.bdo.evolution_native.config;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.bdo.evolution_native.exception.ClientException;
import com.bdo.evolution_native.exception.ClientServerException;
import com.bdo.evolution_native.exception.SystemApiError;
import com.bdo.evolution_native.util.MethodLogger;
import io.netty.handler.logging.LogLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;
import reactor.util.retry.Retry;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * This is for Web Client Configuration
 */
@Component
public class WebClientConfig {

    @Value("${EVOLUTION.RETRY.MAX_ATTEMPT}")
    private long maxAttempt;
    @Value("${EVOLUTION.RETRY.MIN-INTERVAL-IN-MILLIS}")
    private long minInterval;

    /**
     * This method creating instance of WebClient
     *
     * @return WebClient
     */
    @MethodLogger
    public WebClient getWebClient() {
        return WebClient.builder()
            .filter(errorHandler())
            .clientConnector(new ReactorClientHttpConnector(HttpClient.create()
                .wiretap(this.getClass().getCanonicalName(), LogLevel.DEBUG,
                    AdvancedByteBufFormat.TEXTUAL, StandardCharsets.UTF_8)))
            .build();
    }

    @MethodLogger
    public Throwable handleErrors(final Throwable throwable) {
        final Throwable th;
        if (throwable instanceof ClientException || throwable instanceof ClientServerException) {
            th = throwable;
        } else {
            th = new SystemApiError(EvolutionConstantUtils.INVALID_DATA_FROM_SOR, throwable);
        }
        return th;
    }

    /**
     * This method handling error received from SOR
     *
     * @return ExchangeFilterFunction
     */

    @MethodLogger
    private ExchangeFilterFunction errorHandler() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            final Mono<ClientResponse> result;
            if (clientResponse.statusCode().is5xxServerError()) {
                result = clientResponse.bodyToMono(String.class).flatMap(errorBody -> Mono.error(
                    new ClientServerException(clientResponse.rawStatusCode(), errorBody)));
            } else if (clientResponse.statusCode().is4xxClientError()) {
                result = clientResponse.bodyToMono(String.class).flatMap(errorBody -> Mono.error(
                    new ClientException(clientResponse.rawStatusCode(), errorBody)));
            } else {
                result = Mono.just(clientResponse);
            }
            return result;
        });
    }

    /**
     * This method retrying webclient request when connection to SOR not established
     *
     * @return Retry
     */
    @MethodLogger
    public Retry retryForConnectionError() {
        return Retry.backoff(maxAttempt, Duration.ofMillis(minInterval))
            .filter(WebClientRequestException.class::isInstance)
            .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> {
                final WebClientRequestException requestException =
                    (WebClientRequestException) retrySignal.failure();
                throw new ClientException(requestException.getMessage(), requestException);
            });
    }

    /**
     * This method retrying webclient request when server error occurred at SOR
     *
     * @return Retry
     */
    @MethodLogger
    public Retry retryForServerError() {
        return Retry.backoff(maxAttempt, Duration.ofMillis(minInterval))
            .filter(ClientServerException.class::isInstance)
            .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> {
                final ClientServerException exception = (ClientServerException) retrySignal.failure();
                throw new ClientException(exception.getStatus(), exception.getMessage());
            });
    }

}

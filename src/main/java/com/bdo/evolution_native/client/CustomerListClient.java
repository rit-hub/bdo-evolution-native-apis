package com.bdo.evolution_native.client;

import com.bdo.evolution_native.client.model.customerlist.CustListInqRq;
import com.bdo.evolution_native.client.model.customerlist.CustListInqRs;
import com.bdo.evolution_native.config.WebClientConfig;
import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.bdo.evolution_native.exception.EvolutionException;
import com.bdo.evolution_native.exception.SystemApiError;
import com.bdo.evolution_native.util.MethodLogger;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * The type Collateral client.
 */
@Component
@NoArgsConstructor
public class CustomerListClient {

    @Autowired
    private WebClientConfig webClientConfig;
    @Autowired
    private TokenManagerClient tokenManagerClient;
    @Value("${EVOLUTION.BASE_URL}")
    private String baseUrl;
    @Value("${EVOLUTION.CUSTOMER.LIST.PATH}")
    private String path;
    private String url;

    /**
     * Instantiates a new Collateral client.
     *
     * @param webClientConfig The web client config
     */
    public CustomerListClient(final String url,
                              final WebClientConfig webClientConfig,
                              final TokenManagerClient tokenManagerClient) {
        this.url = url;
        this.webClientConfig = webClientConfig;
        this.tokenManagerClient = tokenManagerClient;
    }

    /**
     * This method calling SOR using WebClient.
     *
     * @return CustomerListClient mono
     */
    @MethodLogger
    public Mono<CustListInqRs> customerClient(final CustListInqRq request) {
        return tokenManagerClient.getAccessToken().flatMap(token -> webClientConfig.getWebClient().post()
            .uri(baseUrl + path)
            .accept(MediaType.APPLICATION_JSON)
            .header(EvolutionConstantUtils.AUTHORIZATION_HEADER, EvolutionConstantUtils.BEARER
                + tokenManagerClient.getAccessToken())
            .bodyValue(request)
            .retrieve()
            .bodyToMono(CustListInqRs.class)
            .retryWhen(webClientConfig.retryForServerError())
            .retryWhen(webClientConfig.retryForConnectionError())
            .onErrorMap(webClientConfig::handleErrors)
            .switchIfEmpty(Mono.error(new SystemApiError(EvolutionConstantUtils.EMPTY_SOR_RESPONSE)))
            .map(custListInqRs -> {
                if (!custListInqRs.getStatus()
                    .getStatusCode().equals(EvolutionConstantUtils.ZERO)) {
                    throw new EvolutionException(custListInqRs.getStatus());
                }
                return custListInqRs;
            }));

    }
}

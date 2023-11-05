package com.bdo.evolution_native.client;

import com.bdo.evolution_native.client.model.retrivemodel.CustProfBasicInqRq;
import com.bdo.evolution_native.client.model.retrivemodel.CustProfBasicInqRs;
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
public class RetrieveCustomerBasicDetailsClient {

    @Autowired
    private WebClientConfig webClientConfig;
    @Autowired
    private TokenManagerClient tokenManagerClient;

    @Value("${EVOLUTION.CUSTOMER.Retrieve.PATH}")
    private String path;
    @Value("${EVOLUTION.BASE_URL}")
    private String baseUrl;
    private String url = baseUrl + path;

    /**
     * Instantiates a new Collateral client.
     *
     * @param webClientConfig The web client config
     */
    public RetrieveCustomerBasicDetailsClient(final String url,
                                              final WebClientConfig webClientConfig,
                                              final TokenManagerClient tokenManagerClient) {
        this.url = url;
        this.webClientConfig = webClientConfig;
        this.tokenManagerClient = tokenManagerClient;
    }

    /**
     * This method calling SOR using WebClient.
     *
     * @return RetrieveCustomerBasicDetailsClient mono
     */
    @MethodLogger
    public Mono<CustProfBasicInqRs> retriveCustomerClient(final CustProfBasicInqRq request) {
        return tokenManagerClient.getAccessToken().flatMap(token -> webClientConfig.getWebClient().post()
            .uri(baseUrl + path)
            .accept(MediaType.APPLICATION_JSON)
            .header(EvolutionConstantUtils.AUTHORIZATION_HEADER, EvolutionConstantUtils.BEARER
                + tokenManagerClient.getAccessToken())
            .bodyValue(request)
            .retrieve()
            .bodyToMono(CustProfBasicInqRs.class)
            .retryWhen(webClientConfig.retryForServerError())
            .retryWhen(webClientConfig.retryForConnectionError())
            .onErrorMap(webClientConfig::handleErrors)
            .switchIfEmpty(Mono.error(new SystemApiError(EvolutionConstantUtils.EMPTY_SOR_RESPONSE)))
            .map(customerResponse -> {
                if (!customerResponse.getStatus().getStatusCode().equals(EvolutionConstantUtils.ZERO)) {
                    throw new EvolutionException(customerResponse.getStatus());
                }
                return customerResponse;
            }));
    }
}

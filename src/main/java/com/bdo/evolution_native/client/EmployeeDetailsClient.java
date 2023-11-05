package com.bdo.evolution_native.client;

import com.bdo.evolution_native.client.model.employee.CustEmpAddRq;
import com.bdo.evolution_native.client.model.employee.CustEmpAddRs;
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
public class EmployeeDetailsClient {

    @Autowired
    private WebClientConfig webClientConfig;
    @Autowired
    private TokenManagerClient tokenManagerClient;
    @Value("${EVOLUTION.BASE_URL}")
    private String baseUrl;
    @Value("${EVOLUTION.ACCOUNT.EMPLOYEE_INITIATE.PATH}")
    private String path;
    private String url = baseUrl + path;

    /**
     * Instantiates a new Collateral client.
     *
     * @param webClientConfig The web client config
     */
    public EmployeeDetailsClient(final String url,
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
    public Mono<CustEmpAddRs> employeeClient(final CustEmpAddRq request) {
        return tokenManagerClient.getAccessToken().flatMap(token -> webClientConfig.getWebClient().post()
            .uri(baseUrl + path)
            .accept(MediaType.APPLICATION_JSON)
            .header(EvolutionConstantUtils.AUTHORIZATION_HEADER, EvolutionConstantUtils.BEARER
                + tokenManagerClient.getAccessToken())
            .bodyValue(request)
            .retrieve()
            .bodyToMono(CustEmpAddRs.class)
            .retryWhen(webClientConfig.retryForServerError())
            .retryWhen(webClientConfig.retryForConnectionError())
            .onErrorMap(webClientConfig::handleErrors)
            .switchIfEmpty(Mono.error(new SystemApiError(EvolutionConstantUtils.EMPTY_SOR_RESPONSE)))
            .map(employeeResponse -> {
                if (!employeeResponse.getStatus().getStatusCode().equals(EvolutionConstantUtils.ZERO)) {
                    throw new EvolutionException(employeeResponse.getStatus());
                }
                return employeeResponse;
            }));
    }

}

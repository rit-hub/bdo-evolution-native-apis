package com.bdo.evolution_native.client;

import com.bdo.evolution_native.client.model.BalanceInquiryRequest;
import com.bdo.evolution_native.client.model.BalanceInquiryResponse;
import com.bdo.evolution_native.client.model.LoanBalanceInquiryRequestSor;
import com.bdo.evolution_native.client.model.LoanBalanceInquiryResponseSor;
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
 * This is a web client class for calling the sor
 */
@Component
@NoArgsConstructor
public class BalanceInquiryClient {

    @Autowired
    private TokenManagerClient tokenManagerClient;
    @Autowired
    private WebClientConfig webClientConfig;
    @Value("${EVOLUTION.BASE_URL}")
    private String baseUrl;
    @Value("${EVOLUTION.ACCOUNT.BALANCE_INQUIRY.PATH}")
    private String path;

    private String url = baseUrl + path;

    public BalanceInquiryClient(final String url,
                                final WebClientConfig webClientConfig,
                                final TokenManagerClient tokenManagerClient) {
        this.url = url;
        this.webClientConfig = webClientConfig;
        this.tokenManagerClient = tokenManagerClient;
    }

    /**
     * This method calling SOR using WebClient.
     *
     * @param request
     * @return CollateralClient
     */
    @MethodLogger
    public Mono<BalanceInquiryResponse> getBalanceInquiry(final BalanceInquiryRequest request) {
        return tokenManagerClient.getAccessToken().flatMap(token -> webClientConfig.getWebClient().post()
                .uri(baseUrl + path)
                .accept(MediaType.APPLICATION_JSON)
                .header(EvolutionConstantUtils.AUTHORIZATION_HEADER, EvolutionConstantUtils.BEARER
                        + tokenManagerClient.getAccessToken())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(BalanceInquiryResponse.class)
                .retryWhen(webClientConfig.retryForServerError())
                .retryWhen(webClientConfig.retryForConnectionError())
                .onErrorMap(webClientConfig::handleErrors)
                .switchIfEmpty(Mono.error(new SystemApiError(EvolutionConstantUtils.EMPTY_SOR_RESPONSE)))
                .map(balanceInquiryResponse -> {
                    if (!balanceInquiryResponse.getStatus().getStatusCode().equals(EvolutionConstantUtils.ZERO)) {
                        throw new EvolutionException(balanceInquiryResponse.getStatus());
                    }
                    return balanceInquiryResponse;
                }));
    }

    /**
     * This method calling Loan SOR using WebClient.
     *
     * @param request
     * @return CollateralClient
     */

    @MethodLogger
    public Mono<LoanBalanceInquiryResponseSor> getLoanBalanceInquiry(final LoanBalanceInquiryRequestSor request) {
        return tokenManagerClient.getAccessToken().flatMap(token -> webClientConfig.getWebClient().post()
                .uri(baseUrl + path)
                .accept(MediaType.APPLICATION_JSON)
                .header(EvolutionConstantUtils.AUTHORIZATION_HEADER, EvolutionConstantUtils.BEARER
                        + tokenManagerClient.getAccessToken())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(LoanBalanceInquiryResponseSor.class)
                .retryWhen(webClientConfig.retryForServerError())
                .retryWhen(webClientConfig.retryForConnectionError())
                .onErrorMap(webClientConfig::handleErrors)
                .switchIfEmpty(Mono.error(new SystemApiError(EvolutionConstantUtils.EMPTY_SOR_RESPONSE)))
                .map(loanBalanceInquiryResponseSor -> {
                    if (!loanBalanceInquiryResponseSor.getStatus()
                            .getStatusCode().equals(EvolutionConstantUtils.ZERO)) {
                        throw new EvolutionException(loanBalanceInquiryResponseSor.getStatus());
                    }
                    return loanBalanceInquiryResponseSor;
                }));
    }

}

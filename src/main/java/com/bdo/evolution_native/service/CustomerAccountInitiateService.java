package com.bdo.evolution_native.service;

import com.bdo.evolution_native.model.custacctadd.AccountRequest;
import com.bdo.evolution_native.model.custacctadd.AccountResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

/**
 * The interface Employee details initiate service.
 */
public interface CustomerAccountInitiateService {
    /**
     * Initiate employee details mono.
     */
    Mono<AccountResponse> initiateCustomerAccount(
            AccountRequest accountRequest, ServletUriComponentsBuilder servlet);
}

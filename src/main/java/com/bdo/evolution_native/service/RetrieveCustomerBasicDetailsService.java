package com.bdo.evolution_native.service;

import com.bdo.evolution_native.model.retrivemodel.RetrieveCustomerResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

public interface RetrieveCustomerBasicDetailsService {
    Mono<RetrieveCustomerResponse> retriveCustomer(
        String customerNumber, ServletUriComponentsBuilder servlet);
}

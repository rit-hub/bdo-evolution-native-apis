package com.bdo.evolution_native.service;

import com.bdo.evolution_native.model.initiatemodel.AddCustomerRequest;
import com.bdo.evolution_native.model.initiatemodel.AddCustomerResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

public interface InitiateCustomerBasicDetailsService {
    Mono<AddCustomerResponse> initiateCustomer(
        AddCustomerRequest request, ServletUriComponentsBuilder servlet);
}

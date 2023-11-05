package com.bdo.evolution_native.service;

import com.bdo.evolution_native.model.customerlist.CustomerSearchInquiryRequest;
import com.bdo.evolution_native.model.customerlist.CustomerSearchInquiryResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

/**
 * The interface Customer account search service.
 */
public interface CustomerAccountSearchService {
    /**
     * Retrive customer mono.
     *
     * @return The mono
     */
    Mono<CustomerSearchInquiryResponse> retriveCustomer(
        CustomerSearchInquiryRequest customerSearchInquiryRequest, ServletUriComponentsBuilder servlet);
}

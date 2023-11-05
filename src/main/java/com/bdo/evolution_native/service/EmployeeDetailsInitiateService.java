package com.bdo.evolution_native.service;

import com.bdo.evolution_native.model.employee.CustomerInquireRequest;
import com.bdo.evolution_native.model.employee.CustomerInquireResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

/**
 * The interface Employee details initiate service.
 */
public interface EmployeeDetailsInitiateService {
    /**
     * Initiate employee details mono.
     */
    Mono<CustomerInquireResponse> initiateEmployeeDetails(
        CustomerInquireRequest customerSearchInquiryRequest, ServletUriComponentsBuilder servlet);
}

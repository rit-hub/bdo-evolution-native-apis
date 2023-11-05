package com.bdo.evolution_native.service.impl;

import com.bdo.evolution_native.client.EmployeeDetailsClient;
import com.bdo.evolution_native.client.model.employee.*;
import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.bdo.evolution_native.model.employee.*;
import com.bdo.evolution_native.service.EmployeeDetailsInitiateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * The type Employee details initiate service.
 */
@Service
public class EmployeeDetailsInitiateServiceImpl implements EmployeeDetailsInitiateService {
    @Autowired
    private EmployeeDetailsClient employeeDetailsClient;

    /**
     * The type Employee details initiate method.
     */
    @Override
    public Mono<CustomerInquireResponse> initiateEmployeeDetails(
        final CustomerInquireRequest customerSearchInquiryRequest,
        final ServletUriComponentsBuilder servletUriComponentsBuilder) {
        final CustEmpAddRq custEmpAddRq = mapToCustEmpAddRq(customerSearchInquiryRequest);
        final Mono<CustEmpAddRs> custEmpAddRs = employeeDetailsClient.employeeClient(custEmpAddRq);
        return custEmpAddRs.map(response -> mapToCustomerInquireResponse(response, servletUriComponentsBuilder));
    }

    private CustEmpAddRq mapToCustEmpAddRq(final CustomerInquireRequest request) {
        final CustomerEmploymentDetails customerEmploymentDetails = request.getCustomerEmploymentDetails();
        final CardAccountDetails cardAccountDetails = customerEmploymentDetails.getCardAccountDetails();
        final CustomerId customerId = customerEmploymentDetails.getCustomerProfileBasic().getCustomerId();
        final EmploymentDetails employmentDetails = customerEmploymentDetails.getEmploymentDetails();
        return CustEmpAddRq.builder()
            .rqUID(RqUID.builder().rqUIDField(customerEmploymentDetails.getRequestId()).build())
            .cardAcctId(CardAcctIdType.builder()
                .acctType(cardAccountDetails.getAccountType())
                .acctId(cardAccountDetails.getAccountId())
                .build())
            .custId(CustIdType.builder().custPermId(customerId.getCustomerNumber()).build())
            .employment(CustEmpRecType.builder()
                .empAddr(null)
                .empStartDt(employmentDetails.getEmploymentStartDate())
                .busEmailAddr(employmentDetails.getBusinessEmailAddress())
                .empIncomeAmt(CustProfBasicTypeEmpIncomeAmt.builder()
                    .amt(employmentDetails.getEmployeeIncomeDetails().getEmployeeIncomeAmount())
                    .build())
                .employerId(employmentDetails.getEmployerId())
                .empPhoneAvailCode(employmentDetails.getEmploymentPhoneAvailableCode())
                .faxNumber(employmentDetails.getFaxNumber())
                .phoneNumber(employmentDetails.getPhoneNumber())
                .empStopDt(employmentDetails.getEmploymentStopDate())
                .empType(employmentDetails.getEmploymentType())
                .jobTitleType(employmentDetails.getJobTitleType())
                .sicCode(employmentDetails.getEmployerSicCode())
                .payrollId(employmentDetails.getPayrollId())
                .build())
            .build();
    }

    private CustomerInquireResponse mapToCustomerInquireResponse(
        final CustEmpAddRs response,
        final ServletUriComponentsBuilder servletUriComponentsBuilder) {

        response.setRqUID(Optional.ofNullable(response.getRqUID())
            .orElseGet(RqUID::new));
        response.setCardAcctId(Optional.ofNullable(response.getCardAcctId())
            .orElseGet(CardAcctIdType::new));
        response.setCustId(Optional.ofNullable(response.getCustId())
            .orElseGet(CustIdType::new));

        // Create and populate the CustomerInquireResponseData object
        final CustomerInquireResponseData customerInquireResponseData = CustomerInquireResponseData.builder()
            .requestId(response.getRqUID().getRqUIDField())
            .cardAccountDetails(CustomerInquireResponseDataCardAccountDetails.builder()
                .accountId(response.getCardAcctId().getAcctId())
                .accountIdMasked(response.getCardAcctId().getAcctIdMasked())
                .accountType(response.getCardAcctId().getAcctType())
                .build())
            .customerId(CustomerInquireResponseDataCustomerId.builder()
                .customerNumber(response.getCustId().getCustPermId())
                .customerNumberMasked(response.getCustId().getCustPermIdMasked())
                .build())
            .build();

        // Create and populate the CustomerInquireResponse object
        return CustomerInquireResponse.builder()
            .data(customerInquireResponseData)
            .meta(Meta.builder()
                .totalPages(EvolutionConstantUtils.ONE)
                .firstAvailableDateTime(EvolutionConstantUtils.EMPTY_STRING)
                .lastAvailableDateTime(EvolutionConstantUtils.EMPTY_STRING)
                .build())
            .links(Links.builder()
                .first(EvolutionConstantUtils.EMPTY_STRING)
                .last(EvolutionConstantUtils.EMPTY_STRING)
                .next(EvolutionConstantUtils.EMPTY_STRING)
                .prev(EvolutionConstantUtils.EMPTY_STRING)
                .self(servletUriComponentsBuilder.build().toString())
                .build())
            .build();
    }
}

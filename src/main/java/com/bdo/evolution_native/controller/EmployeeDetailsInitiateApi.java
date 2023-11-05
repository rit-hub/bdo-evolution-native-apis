package com.bdo.evolution_native.controller;

import com.bdo.evolution_native.model.ErrorResponse;
import com.bdo.evolution_native.model.employee.CustomerInquireRequest;
import com.bdo.evolution_native.model.employee.CustomerInquireResponse;
import com.bdo.evolution_native.service.EmployeeDetailsInitiateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import static com.bdo.evolution_native.constants.EvolutionConstantUtils.*;

@RestController
@Validated
public class EmployeeDetailsInitiateApi {

    @Autowired
    private EmployeeDetailsInitiateService employeeDetailsInitiateService;
    private final Logger logger = LoggerFactory.getLogger(EmployeeDetailsInitiateApi.class);

    /**
     * Build call for Creation of customer inquire
     *
     * @return The mono
     */
    //Suppressing warning for complexity for regex pattern of x-fapi-auth-date
    //and code duplication with other account balance inquiry controller
    @SuppressWarnings({"java:S5843", "CPD-START"})
    @ApiOperation(value = "Employee Details Initiate",
        nickname = "Add Employee Details",
        response = CustomerInquireResponse.class)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Created",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = CustomerInquireResponse.class))),
        @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden",
            content = @Content(mediaType = "application/json; charset&#x3D;utf-8",
                schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "405", description = "Method Not Allowed"),
        @ApiResponse(responseCode = "406", description = "Not Acceptable"),
        @ApiResponse(responseCode = "422", description = "The server understands the content type of the request "
            + "entity, but it was unable to process the contained instructions",
            content = @Content(mediaType = "application/json; charset&#x3D;utf-8",
                schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "429", description = "Too Many Requests"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(mediaType = "application/json; charset&#x3D;utf-8",
                schema = @Schema(implementation = ErrorResponse.class)))})
    @PostMapping(value = "/PartyReference/CustomerProfile/EmployeeDetails/Initiate",
        produces = {"application/json", "application/json; charset=utf-8", "application/jose+jwe"},
        consumes = {"application/json"})
    public Mono<ResponseEntity<CustomerInquireResponse>> empDetailsInitiateCall(
        @ApiParam(value = "The time when the PSU last logged in with the TPP. All dates in the HTTP headers are "
            + "represented as RFC 7231 Full Dates. An example is below: Sun, 10 Sep 2017 19:43:31 UTC")
        @Valid @Pattern(regexp = "(^$)|(^(Mon|Tue|Wed|Thu|Fri|Sat|Sun), "
            + "\\d{2} (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{4} \\d{2}:\\d{2}:"
            + "\\d{2} (GMT|UTC)$)", message = "{auth-date.invalid.header}")
        @RequestHeader(value = "x-fapi-auth-date", required = false) final String xFapiAuthDate,
        @ApiParam(value = "The PSU's IP address if the PSU is currently logged in with the TPP.")
        @RequestHeader(value = "x-fapi-customer-ip-address", required = false) final String xFapiCustomerIpAddress,
        @ApiParam(value = "An RFC4122 UID used as a correlation id.")
        @RequestHeader(value = "x-fapi-interaction-id", required = false) final String xFapiInteractionId,
        @ApiParam(value = "Indicates the user-agent that the PSU is using.")
        @RequestHeader(value = "x-customer-user-agent", required = false) final String xCustomerUserAgent,
        @ApiParam(value = "Generated Id.")
        @RequestHeader(value = "Generated_id", required = false) final String generatedId,
        @Parameter(in = ParameterIn.DEFAULT, description = "", required = true,
            schema = @Schema())
        @Valid @RequestBody(required = true) final CustomerInquireRequest customerInquireRequest
    ) {
        logger.debug(AUTH_DATE + CUST_ADDRESS + INTERACTION_ID + CUST_USER_AGENT,
            xFapiAuthDate, xFapiCustomerIpAddress, xFapiInteractionId, xCustomerUserAgent);
        final Mono<CustomerInquireResponse> responseMono = employeeDetailsInitiateService.initiateEmployeeDetails(
            customerInquireRequest, ServletUriComponentsBuilder.fromCurrentRequest());
        return responseMono.map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }
}

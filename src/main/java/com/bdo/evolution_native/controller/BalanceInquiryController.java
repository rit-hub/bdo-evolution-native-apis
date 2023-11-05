package com.bdo.evolution_native.controller;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.bdo.evolution_native.model.CurrentBalanceInquiryResponse;
import com.bdo.evolution_native.model.ErrorResponse;
import com.bdo.evolution_native.model.SavingBalanceInquiryResponse;
import com.bdo.evolution_native.model.TimeDepositBalanceInquiryResponse;
import com.bdo.evolution_native.model.loan.model.LoanBalanceInquiryResponse;
import com.bdo.evolution_native.service.BalanceInquiryService;
import com.bdo.evolution_native.util.MethodLogger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.bdo.evolution_native.constants.EvolutionConstantUtils.AUTH_DATE;
import static com.bdo.evolution_native.constants.EvolutionConstantUtils.CUST_ADDRESS;
import static com.bdo.evolution_native.constants.EvolutionConstantUtils.CUST_USER_AGENT;
import static com.bdo.evolution_native.constants.EvolutionConstantUtils.INTERACTION_ID;

/**
 * This is Balance Inquiry Controller for different type of account
 */
@RestController
@Validated
@Api(value = "Account Balance Inquiry", tags = "The Account Balance Inquiry API")
public class BalanceInquiryController {

    private final Logger logger = LoggerFactory.getLogger(BalanceInquiryController.class);
    private BalanceInquiryService balanceInquiryService;

    public BalanceInquiryController(final BalanceInquiryService balanceInquiryService) {
        this.balanceInquiryService = balanceInquiryService;
    }

    /**
     * Retrieves the balances of a Current Account.
     *
     * @param currentAccountId       This is the Current Account Number. (required)
     * @param xFapiAuthDate          The time when the PSU last logged in with the TPP.  All dates in the HTTP
     *                               headers are represented as RFC 7231 Full Dates. An example is below:
     *                               Sun, 10 Sep 2017 19:43:31 UTC (optional)
     * @param xFapiCustomerIpAddress The PSU&#x27;s IP address if the PSU is currently
     *                               logged in with the TPP. (optional)
     * @param xFapiInteractionId     An RFC4122 UID used as a correlation id. (optional)
     * @param xCustomerUserAgent     Indicates the user-agent that the PSU is using. (optional)
     * @return CurrentBalanceInquiryResponse
     * @return Success (status code 200)
     * or Bad request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Method Not Allowed (status code 405)
     * or Not Acceptable (status code 406)
     * or The server understands the content type of the request entity,
     * but it was unable to process the contained instructions (status code 422)
     * or Too Many Requests (status code 429)
     * or Internal Server Error (status code 500)
     */
    @ApiOperation(value = "Retrieve the balances of current account",
        nickname = "Retrieve Current Account Balance API",
        notes = "The Current Account or Demand Deposit Account as it can be called is a financial "
            + "facility that represents the general checking account facility at the core of the "
            + "consumer and small business banking activity. There is a range of supporting "
            + "features that make up the standard facility (checks/cheques, standing orders, "
            + "debit cards, phone and on-line banking services etc.). The fulfillment can include "
            + "a range of optional services, fees and interest payments and penalties and will often "
            + "be bundled and or underlie/support other financial facilities and financial transactions..",
        response = CurrentBalanceInquiryResponse.class)
    //Suppressing warning for complexity for regex pattern of x-fapi-auth-date
    //and code duplication with other account balance inquiry controller
    @SuppressWarnings({"java:S5843", "CPD-START"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = CurrentBalanceInquiryResponse.class),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
        @ApiResponse(code = 405, message = "Method Not Allowed"),
        @ApiResponse(code = 406, message = "Not Acceptable"),
        @ApiResponse(code = 422, message = "The server understands the content type of the request entity, "
            + "but it was unable to process the contained instructions", response = ErrorResponse.class),
        @ApiResponse(code = 429, message = "Too Many Requests"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)})
    @MethodLogger
    @GetMapping(
        value = "/CurrentAccount/Retrieve",
        produces = "application/json"
    )
    public Mono<ResponseEntity<CurrentBalanceInquiryResponse>> getCurrentAccountBalance(
        @ApiParam(value = "This is the Current Account Number.", required = true)
        @RequestHeader(value = "CurrentAccountId")
        @Valid @NotBlank(message = "{CurrentAccountId.header.missing}")
        @Size(max = 12, message = "{CurrentAccountId.header.invalid.size}")
        @Pattern(regexp = "(^$)|(^[\\d]+$)", message = "{CurrentAccountId.invalid.header}")
        final String currentAccountId,
        @ApiParam(value = "The time when the PSU last logged in with the TPP.  All dates in the HTTP headers are "
            + "represented as RFC 7231 Full Dates. An example is below:  Sun, 10 Sep 2017 19:43:31 UTC")
        @Valid @Pattern(regexp = "(^$)|(^(Mon|Tue|Wed|Thu|Fri|Sat|Sun), "
            + "\\d{2} (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{4} \\d{2}:\\d{2}:"
            + "\\d{2} (GMT|UTC)$)", message = "{auth-date.invalid.header}")
        @RequestHeader(value = "x-fapi-auth-date", required = false) final String xFapiAuthDate,
        @ApiParam(value = "The PSU's IP address if the PSU is currently logged in with the TPP.")
        @RequestHeader(value = "x-fapi-customer-ip-address", required = false) final String xFapiCustomerIpAddress,
        @ApiParam(value = "An RFC4122 UID used as a correlation id.")
        @RequestHeader(value = "x-fapi-interaction-id", required = false) final String xFapiInteractionId,
        @ApiParam(value = "Indicates the user-agent that the PSU is using.")
        @RequestHeader(value = "x-customer-user-agent", required = false) final String xCustomerUserAgent) {
        logger.debug(AUTH_DATE + CUST_ADDRESS + INTERACTION_ID + CUST_USER_AGENT,
            xFapiAuthDate, xFapiCustomerIpAddress, xFapiInteractionId, xCustomerUserAgent);
        return balanceInquiryService.getCurrentAccountBalance(currentAccountId,
                        ServletUriComponentsBuilder.fromCurrentRequest())
            .map(ResponseEntity::ok);

    }

    /**
     * Retrieves the balances of a Savings Account.
     *
     * @param savingsAccountId       This is the savings Account Number. (required)
     * @param xFapiAuthDate          The time when the PSU last logged in with the TPP.  All dates in the HTTP
     *                               headers are represented as RFC 7231 Full Dates. An example is below:
     *                               Sun, 10 Sep 2017 19:43:31 UTC (optional)
     * @param xFapiCustomerIpAddress The PSU&#x27;s IP address if the PSU is currently
     *                               logged in with the TPP. (optional)
     * @param xFapiInteractionId     An RFC4122 UID used as a correlation id. (optional)
     * @param xCustomerUserAgent     Indicates the user-agent that the PSU is using. (optional)
     * @return SavingBalanceInquiryResponse
     * @return Success (status code 200)
     * or Bad request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Method Not Allowed (status code 405)
     * or Not Acceptable (status code 406)
     * or The server understands the content type of the request entity,
     * but it was unable to process the contained instructions (status code 422)
     * or Too Many Requests (status code 429)
     * or Internal Server Error (status code 500)
     */
    //Suppressing warning for complexity for regex pattern of x-fapi-auth-date
    //and code duplication with other account balance inquiry controller
    @SuppressWarnings({"java:S5843", "CPD-START"})
    @ApiOperation(value = "Retrieve the balances of savings account",
        nickname = "Retrieve Savings Account Balance API",
        notes = "The savings account product may share many features with a standard bank current account "
            + "(e.g. standing orders, direct debits, payments and deposit services) with some "
            + "constraints/limitations. "
            + "There can be restrictions on the amount and frequency of withdrawals with an associated "
            + "fee/penalty structure to offset higher interest benefits. Sweep mechanisms linked to other products "
            + "(current account) can also be supported.",
        response = SavingBalanceInquiryResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = SavingBalanceInquiryResponse.class),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
        @ApiResponse(code = 405, message = "Method Not Allowed"),
        @ApiResponse(code = 406, message = "Not Acceptable"),
        @ApiResponse(code = 422, message = "The server understands the content type of the request entity, "
            + "but it was unable to process the contained instructions", response = ErrorResponse.class),
        @ApiResponse(code = 429, message = "Too Many Requests"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)})
    @MethodLogger
    @GetMapping(
        value = "/SavingsAccount/Retrieve",
        produces = "application/json"
    )
    public Mono<ResponseEntity<SavingBalanceInquiryResponse>> getSavingsAccountBalance(
        @ApiParam(value = "This is the Savings Account Number.", required = true)
        @RequestHeader(value = "SavingsAccountId")
        @Valid @NotBlank(message = "{SavingsAccountId.header.missing}")
        @Size(max = 12, message = "{SavingsAccountId.header.invalid.size}")
        @Pattern(regexp = "(^$)|(^[\\d]+$)", message = "{SavingsAccountId.invalid.header}")
        final String savingsAccountId,
        @ApiParam(value = "The time when the PSU last logged in with the TPP.  All dates in the HTTP headers are "
            + "represented as RFC 7231 Full Dates. An example is below:  Sun, 10 Sep 2017 19:43:31 UTC")
        @Valid @Pattern(regexp = "(^$)|(^(Mon|Tue|Wed|Thu|Fri|Sat|Sun), "
            + "\\d{2} (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{4} \\d{2}:\\d{2}:"
            + "\\d{2} (GMT|UTC)$)", message = "{auth-date.invalid.header}")
        @RequestHeader(value = "x-fapi-auth-date", required = false) final String xFapiAuthDate,
        @ApiParam(value = "The PSU's IP address if the PSU is currently logged in with the TPP.")
        @RequestHeader(value = "x-fapi-customer-ip-address", required = false) final String xFapiCustomerIpAddress,
        @ApiParam(value = "An RFC4122 UID used as a correlation id.")
        @RequestHeader(value = "x-fapi-interaction-id", required = false) final String xFapiInteractionId,
        @ApiParam(value = "Indicates the user-agent that the PSU is using.")
        @RequestHeader(value = "x-customer-user-agent", required = false) final String xCustomerUserAgent) {
        logger.debug(AUTH_DATE + CUST_ADDRESS + INTERACTION_ID + CUST_USER_AGENT,
            xFapiAuthDate, xFapiCustomerIpAddress, xFapiInteractionId, xCustomerUserAgent);
        return balanceInquiryService.getSavingsAccountBalance(savingsAccountId,
                        ServletUriComponentsBuilder.fromCurrentRequest())
            .map(ResponseEntity::ok);

    }

    /**
     * Retrieves the balances of a Time Deposit Account.
     *
     * @param timeDepositId          This is the time deposit Account Number. (required)
     * @param xFapiAuthDate          The time when the PSU last logged in with the TPP.  All dates in the HTTP
     *                               headers are represented as RFC 7231 Full Dates. An example is below:
     *                               Sun, 10 Sep 2017 19:43:31 UTC (optional)
     * @param xFapiCustomerIpAddress The PSU&#x27;s IP address if the PSU is currently
     *                               logged in with the TPP. (optional)
     * @param xFapiInteractionId     An RFC4122 UID used as a correlation id. (optional)
     * @param xCustomerUserAgent     Indicates the user-agent that the PSU is using. (optional)
     * @return TimeDepositBalanceInquiryResponse
     * @return Success (status code 200)
     * or Bad request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Method Not Allowed (status code 405)
     * or Not Acceptable (status code 406)
     * or The server understands the content type of the request entity,
     * but it was unable to process the contained instructions (status code 422)
     * or Too Many Requests (status code 429)
     * or Internal Server Error (status code 500)
     */
    //Suppressing warning for complexity for regex pattern of x-fapi-auth-date
    //and code duplication with other account balance inquiry controller
    @SuppressWarnings({"java:S5843", "CPD-START"})
    @ApiOperation(value = "Retrieve the balances of Time Deposit account",
        nickname = "Retrieve Time Deposit Account Balance API",
        notes = "A time deposit is an agreement between a customer and the bank to place "
            + "a fixed amount of funds for a fixed amount of time in an interest bearing account. "
            + "Customers may want to place several amounts in deposit accounts in parallel and "
            + "over time. In order to avoid renewed negotiations about standard conditions, a customer "
            + "and the bank may agree on an overal framework agreement for these standard conditions. "
            + "Under this framework agreement individual time deposits can be opened more efficiently. "
            + "The current Service Domain handles both the individual time deposits and the overall "
            + "framework agreement.",
        response = TimeDepositBalanceInquiryResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = TimeDepositBalanceInquiryResponse.class),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
        @ApiResponse(code = 405, message = "Method Not Allowed"),
        @ApiResponse(code = 406, message = "Not Acceptable"),
        @ApiResponse(code = 422, message = "The server understands the content type of the request entity, "
            + "but it was unable to process the contained instructions", response = ErrorResponse.class),
        @ApiResponse(code = 429, message = "Too Many Requests"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)})
    @MethodLogger
    @GetMapping(
        value = "/TimeDeposit/Retrieve",
        produces = "application/json"
    )
    public Mono<ResponseEntity<TimeDepositBalanceInquiryResponse>> getTimeDepositAccountBalance(
        @ApiParam(value = "This is the Time Deposit Account Number.", required = true)
        @RequestHeader(value = "TimeDepositId")
        @Valid @NotBlank(message = "{TimeDepositId.header.missing}")
        @Size(max = 12, message = "{TimeDepositId.header.invalid.size}")
        @Pattern(regexp = "(^$)|(^[\\d]+$)", message = "{TimeDepositId.invalid.header}") final String timeDepositId,
        @ApiParam(value = "The time when the PSU last logged in with the TPP.  All dates in the HTTP headers are "
            + "represented as RFC 7231 Full Dates. An example is below:  Sun, 10 Sep 2017 19:43:31 UTC")
        @Valid @Pattern(regexp = "(^$)|(^(Mon|Tue|Wed|Thu|Fri|Sat|Sun), "
            + "\\d{2} (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{4} \\d{2}:\\d{2}:"
            + "\\d{2} (GMT|UTC)$)", message = "{auth-date.invalid.header}")
        @RequestHeader(value = "x-fapi-auth-date", required = false) final String xFapiAuthDate,
        @ApiParam(value = "The PSU's IP address if the PSU is currently logged in with the TPP.")
        @RequestHeader(value = "x-fapi-customer-ip-address", required = false) final String xFapiCustomerIpAddress,
        @ApiParam(value = "An RFC4122 UID used as a correlation id.")
        @RequestHeader(value = "x-fapi-interaction-id", required = false) final String xFapiInteractionId,
        @ApiParam(value = "Indicates the user-agent that the PSU is using.")
        @RequestHeader(value = "x-customer-user-agent", required = false) final String xCustomerUserAgent) {
        logger.debug(AUTH_DATE + CUST_ADDRESS + INTERACTION_ID + CUST_USER_AGENT,
            xFapiAuthDate, xFapiCustomerIpAddress, xFapiInteractionId, xCustomerUserAgent);
        return balanceInquiryService.getTimeDepositAccountBalance(timeDepositId,
                        ServletUriComponentsBuilder.fromCurrentRequest())
            .map(ResponseEntity::ok);

    }

    /**
     * Retrieves the balances of a Loan Account.
     *
     * @param loanAccountId          This is the Loan Account Number. (required)
     * @param xFapiAuthDate          The time when the PSU last logged in with the TPP.  All dates in the HTTP
     *                               headers are represented as RFC 7231 Full Dates. An example is below:
     *                               Sun, 10 Sep 2017 19:43:31 UTC (optional)
     * @param xFapiCustomerIpAddress The PSU&#x27;s IP address if the PSU is currently
     *                               logged in with the TPP. (optional)
     * @param xFapiInteractionId     An RFC4122 UID used as a correlation id. (optional)
     * @param xCustomerUserAgent     Indicates the user-agent that the PSU is using. (optional)
     * @return LoanBalanceInquiryResponse
     * @return Success (status code 200)
     * or Bad request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Method Not Allowed (status code 405)
     * or Not Acceptable (status code 406)
     * or The server understands the content type of the request entity,
     * but it was unable to process the contained instructions (status code 422)
     * or Too Many Requests (status code 429)
     * or Internal Server Error (status code 500)
     */
    @ApiOperation(value = "Retrieve the balances of loan account",
        nickname = "Retrieve loan Account Balance API",
            notes = "The loan product represents a generic loan capability that "
                    + "may include different properties/features depending on a bank's "
                    + "preferences and policies.The loan may or may not be secured by collateral.",
        response = LoanBalanceInquiryResponse.class)
    //Suppressing warning for complexity for regex pattern of x-fapi-auth-date
    //and code duplication with other account balance inquiry controller
    @SuppressWarnings({"java:S5843", "CPD-START"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = CurrentBalanceInquiryResponse.class),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
        @ApiResponse(code = 405, message = "Method Not Allowed"),
        @ApiResponse(code = 406, message = "Not Acceptable"),
        @ApiResponse(code = 422, message = "The server understands the content type of the request entity, "
            + "but it was unable to process the contained instructions", response = ErrorResponse.class),
        @ApiResponse(code = 429, message = "Too Many Requests"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)})
    @MethodLogger
    @GetMapping(
        value = "/Loan/Retrieve",
        produces = "application/json"
    )
    public Mono<ResponseEntity<LoanBalanceInquiryResponse>> getLoanAccountBalance(
        @ApiParam(value = "This is the Current Account Number.", required = true)
        @RequestHeader(value = "LoanId")
        @Valid @NotBlank(message = EvolutionConstantUtils.LOAN_ID_HEADER_MISSING_VALIDATION)
        @Size(max = 12, message = EvolutionConstantUtils.LOAN_ID_HEADER_SIZE_VALIDATION)
        @Pattern(regexp = "(^$)|(^[\\d]+$)", message = EvolutionConstantUtils.LOAN_ID_INVALID_HEADER_VALIDATION)
        final String loanAccountId,
        @ApiParam(value = "The time when the PSU last logged in with the TPP.  All dates in the HTTP headers are "
            + "represented as RFC 7231 Full Dates. An example is below:  Sun, 10 Sep 2017 19:43:31 UTC")
        @Valid @Pattern(regexp = "(^$)|(^(Mon|Tue|Wed|Thu|Fri|Sat|Sun), "
            + "\\d{2} (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{4} \\d{2}:\\d{2}:"
            + "\\d{2} (GMT|UTC)$)", message = "{auth-date.invalid.header}")
        @RequestHeader(value = "x-fapi-auth-date", required = false) final String xFapiAuthDate,
        @ApiParam(value = "The PSU's IP address if the PSU is currently logged in with the TPP.")
        @RequestHeader(value = "x-fapi-customer-ip-address", required = false) final String xFapiCustomerIpAddress,
        @ApiParam(value = "An RFC4122 UID used as a correlation id.")
        @RequestHeader(value = "x-fapi-interaction-id", required = false) final String xFapiInteractionId,
        @ApiParam(value = "Indicates the user-agent that the PSU is using.")
        @RequestHeader(value = "x-customer-user-agent", required = false) final String xCustomerUserAgent) {
        logger.debug(AUTH_DATE + CUST_ADDRESS + INTERACTION_ID + CUST_USER_AGENT,
            xFapiAuthDate, xFapiCustomerIpAddress, xFapiInteractionId, xCustomerUserAgent);
        return balanceInquiryService.getLoanAccountBalance(loanAccountId,
                        ServletUriComponentsBuilder.fromCurrentRequest())
            .map(ResponseEntity::ok);

    }

}

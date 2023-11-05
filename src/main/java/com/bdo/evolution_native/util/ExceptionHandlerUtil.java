package com.bdo.evolution_native.util;

import com.bdo.error.bdoerrorcodes.exceptions.ErrorCodeException;
import com.bdo.error.bdoerrorcodes.utility.enums.Layer;
import com.bdo.evolution_native.client.model.StatusType;
import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.bdo.evolution_native.enums.Domain;
import com.bdo.evolution_native.enums.ErrorCodes;
import com.bdo.evolution_native.enums.HostErrorCodes;
import com.bdo.evolution_native.exception.EvolutionException;
import com.bdo.evolution_native.model.Error;
import com.bdo.evolution_native.model.ErrorResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.bdo.evolution_native.constants.EvolutionConstantUtils.*;

/**
 * This is for Exception Handler Util
 */
@Component
@Slf4j
public class ExceptionHandlerUtil {

    /**
     * This method is building error response.
     *
     * @param errorType
     * @param ex
     * @param request
     * @param status
     * @return ErrorResponse
     */
    @MethodLogger
    public ErrorResponse buildErrorResponse(final String errorType, final Exception ex,
                                            final HttpServletRequest request, final HttpStatus status) {
        printLogTrace(ex.getMessage(), ex);
        final ErrorResponse errorResponse;
        if (ex instanceof BindException || ex instanceof ConstraintViolationException) {
            errorResponse = bindMultipleErrorResponseBuilder(errorType, ex, request, status);
        } else {
            String message = ex.getMessage();
            if (Objects.isNull(message)) {
                message = EvolutionConstantUtils.EXCEPTION_MESSAGE_NULL;
            }

            final ErrorCodeException errorCodeEx = new ErrorCodeException(
                Layer.MICROSERVICE, Domain.EVOLUTION.toCode(),
                Integer.toString(status.value()), message);
            errorResponse = buildErrorResponseCommon(errorCodeEx, message);
            errorResponse.addError(buildErrors(message, request));
            printErrorLog(request, errorType, ex, errorResponse);
        }
        return errorResponse;
    }

    /**
     * This method is building error response for BindException.
     *
     * @param errorType
     * @param ex
     * @param request
     * @param status
     * @return ErrorResponse
     */
    @MethodLogger
    private ErrorResponse bindMultipleErrorResponseBuilder(final String errorType,
                                                           final Exception ex, final HttpServletRequest request,
                                                           final HttpStatus status) {

        final ErrorCodeException errorCodeEx = new ErrorCodeException(
            Layer.MICROSERVICE, Domain.EVOLUTION.toCode(),
            Integer.toString(status.value()), ex.getMessage());
        final ErrorResponse errorResponse = buildErrorResponseCommon(errorCodeEx,
            EvolutionConstantUtils.VALIDATION_FAILED);
        if (ex instanceof BindException) {
            final BindException exception = (BindException) ex;
            exception.getFieldErrors().forEach(fieldError -> {
                final String message = fieldError.getDefaultMessage();
                errorResponse.addError(buildErrors(message, request));
            });
        } else {
            final ConstraintViolationException exception = (ConstraintViolationException) ex;
            exception.getConstraintViolations().forEach(fieldError -> {
                final String message = fieldError.getMessage();
                errorResponse.addError(buildErrors(message, request));
            });
        }
        log.error(errorType,
            ex.getClass().getName(),
            errorResponse, errorResponse.getMessage());
        return errorResponse;
    }

    /**
     * This method is building errors
     *
     * @param message
     * @param request
     * @return Error
     */
    @MethodLogger
    private Error buildErrors(final String message, final HttpServletRequest request) {
        String errorMessage = message;
        if (!errorMessage.contains(EvolutionConstantUtils.ERROR_CODE_NAME)) {
            errorMessage = errorMessage + " " + ErrorCodes.UNEXPECTEDERROR.toCode();
        }
        final String errCode = errorMessage.substring(errorMessage.indexOf(EvolutionConstantUtils.ERROR_CODE_NAME));
        errorMessage = errorMessage.substring(0, errorMessage.indexOf(EvolutionConstantUtils.ERROR_CODE_NAME)).trim();
        return new Error(errCode, errorMessage,
            request.getRequestURI(), request.getRequestURL().toString());
    }

    /**
     * This method is building common error response.
     *
     * @param errorCodeEx
     * @param errorMessage
     * @return ErrorResponse
     */
    @MethodLogger
    private ErrorResponse buildErrorResponseCommon(final ErrorCodeException errorCodeEx, final String errorMessage) {
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(errorCodeEx.toCode());
        errorResponse.setMessage(errorMessage);
        errorResponse.setId(Integer.toString(errorCodeEx.hashCode()));
        return errorResponse;
    }

    /**
     * This method building headers and returning.
     *
     * @param request
     * @return HttpHeaders
     */
    @MethodLogger
    public HttpHeaders getHeaders(final HttpServletRequest request) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON + EvolutionConstantUtils.CHARSET_UTF8));
        if (!StringUtils.isEmpty(request.getHeader(EvolutionConstantUtils.INTERACTION_ID_HEADER))) {
            headers.add(EvolutionConstantUtils.INTERACTION_ID_HEADER,
                request.getHeader(EvolutionConstantUtils.INTERACTION_ID_HEADER));
        }
        return headers;
    }

    /**
     * This method giving message by status code received from SOR
     *
     * @param status
     * @return String
     */
    @MethodLogger
    public String getClientMessage(final Integer status) {
        final Map<Integer, String> statusMessageMap = Map.of(
            HttpStatus.BAD_REQUEST.value(), EvolutionConstantUtils.SOR_INVALID_REQUEST
                + EvolutionConstantUtils.SPACE + ErrorCodes.BAD_REQUEST.toCode(),
            HttpStatus.NOT_FOUND.value(), EvolutionConstantUtils.SOR_NOT_FOUND
                + EvolutionConstantUtils.SPACE + ErrorCodes.NOT_FOUND.toCode(),
            HttpStatus.FORBIDDEN.value(), EvolutionConstantUtils.SOR_ACCESS_DENIED
                + EvolutionConstantUtils.SPACE + ErrorCodes.FORBIDDEN.toCode(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(), EvolutionConstantUtils.SOR_SERVER_ERROR
                + EvolutionConstantUtils.SPACE + ErrorCodes.INTERNAL_SERVER_ERROR.toCode(),
            HttpStatus.UNAUTHORIZED.value(), EvolutionConstantUtils.SOR_UNAUTHORIZED
                + EvolutionConstantUtils.SPACE + ErrorCodes.UNAUTHORIZED.toCode(),
            HttpStatus.REQUEST_TIMEOUT.value(), EvolutionConstantUtils.SOR_TIMEOUT
                + EvolutionConstantUtils.SPACE + ErrorCodes.REQUESTTIMEOUT.toCode()
        );

        return statusMessageMap.getOrDefault(status, status + EvolutionConstantUtils.SPACE
            + EvolutionConstantUtils.SOR_ANY_ERROR + EvolutionConstantUtils.SPACE
            + ErrorCodes.UNEXPECTEDERROR.toCode());
    }

    /**
     * This method giving message by status code received from Authorization Server
     *
     * @param status
     * @return String
     */
    @MethodLogger
    public String getOauthClientMessage(final Integer status) {
        final String message;
        final int statusCode = Objects.nonNull(status) ? status : -1;

        switch (HttpStatus.valueOf(statusCode)) {
            case BAD_REQUEST:
                message = EvolutionConstantUtils.OAUTH_INVALID_REQUEST + EvolutionConstantUtils.SPACE
                    + ErrorCodes.BAD_REQUEST.toCode();
                break;
            case NOT_FOUND:
                message = EvolutionConstantUtils.OAUTH_NOT_FOUND + EvolutionConstantUtils.SPACE
                    + ErrorCodes.NOT_FOUND.toCode();
                break;
            case FORBIDDEN:
                message = EvolutionConstantUtils.OAUTH_ACCESS_DENIED + EvolutionConstantUtils.SPACE
                    + ErrorCodes.FORBIDDEN.toCode();
                break;
            case INTERNAL_SERVER_ERROR:
                message = EvolutionConstantUtils.OAUTH_SERVER_ERROR + EvolutionConstantUtils.SPACE
                    + ErrorCodes.INTERNAL_SERVER_ERROR.toCode();
                break;
            case UNAUTHORIZED:
                message = EvolutionConstantUtils.OAUTH_UNAUTHORIZED + EvolutionConstantUtils.SPACE
                    + ErrorCodes.UNAUTHORIZED.toCode();
                break;
            case REQUEST_TIMEOUT:
                message = EvolutionConstantUtils.OAUTH_TIMEOUT + EvolutionConstantUtils.SPACE
                    + ErrorCodes.REQUESTTIMEOUT.toCode();
                break;
            default:
                message = statusCode + EvolutionConstantUtils.SPACE + EvolutionConstantUtils.OAUTH_ANY_ERROR
                    + EvolutionConstantUtils.SPACE + ErrorCodes.UNEXPECTEDERROR.toCode();
                break;
        }
        return message;
    }

    /**
     * This method print log for error
     *
     * @param errorType
     * @param ex
     * @param errorResponse
     */
    @MethodLogger
    public void printErrorLog(final HttpServletRequest request, final String errorType,
                              final Exception ex, final ErrorResponse errorResponse) {
        final Map<String, String> headerMap = new ConcurrentHashMap<>();
        final Enumeration<String> headerNames = request.getHeaderNames();
        while (Objects.nonNull(headerNames) && headerNames.hasMoreElements()) {
            final String headerName = headerNames.nextElement();
            final String headerValue = request.getHeader(headerName);
            switch (headerName) {
                case AUTH_DATE_CONST:
                case CUST_ADDRESS_CONST:
                case INTERACTION_ID_CONST:
                case CUST_USER_AGENT_CONST:
                case GENERATED_ID_CONST:
                    headerMap.put(headerName, headerValue);
                    break;
                default:
                    break;
            }
        }
        log.error(errorType, HEADERS + headerMap,
            ex.getClass().getName(), errorResponse, errorResponse.getMessage());
    }

    /**
     * This method print the trace of Exception
     *
     * @param message
     * @param exception
     */
    @MethodLogger
    public void printLogTrace(final String message, final Exception exception) {
        log.error(message, exception);
    }

    /**
     * This method build error response for EvolutionException
     *
     * @param errorType
     * @param ex
     * @param request
     * @param status
     * @return ErrorResponse
     */
    @SneakyThrows
    @MethodLogger
    public ErrorResponse errorResponse(final String errorType, final EvolutionException ex,
                                       final HttpServletRequest request, final HttpStatus status) {
        final String message = getClientMessage(status.value());
        printLogTrace(message, ex);
        final ErrorCodeException errorCodeEx = new ErrorCodeException(
            Layer.MICROSERVICE, Domain.EVOLUTION.toCode(),
            Integer.toString(status.value()), message);
        final ErrorResponse errorResponse = buildErrorResponseCommon(errorCodeEx, message);
        errorResponse.setErrors(getEvolutionErrors(ex.getStatus(), request));
        printErrorLog(request, errorType, ex, errorResponse);
        return errorResponse;
    }

    /**
     * Building Errors from Evolution errors
     *
     * @param status
     * @param request
     * @return
     */
    @MethodLogger
    private List<Error> getEvolutionErrors(final StatusType status, final HttpServletRequest request) {
        final List<Error> errorList = new ArrayList<>();
        Optional.ofNullable(status.getErrors()).ifPresent(errors -> errors.forEach(er -> {
            final Error error = new Error();
            final HttpStatus httpStatus = getStatusFromHostCode(er.getHostErrorCode());
            error.setErrorCode(getErrorCode(httpStatus));
            error.setPath(request.getRequestURI());
            error.setUrl(request.getRequestURL().toString());
            error.setMessage(er.getHostErrorCode() + EvolutionConstantUtils.COLON + er.getErrorDescription());
            errorList.add(error);
        }));
        Optional.ofNullable(status.getWarnings()).ifPresent(warning -> warning.forEach(warn -> {
            final Error error = new Error();
            final HttpStatus httpStatus = getStatusFromHostCode(warn.getHostErrorCode());
            error.setErrorCode(getErrorCode(httpStatus));
            error.setPath(request.getRequestURI());
            error.setUrl(request.getRequestURL().toString());
            error.setMessage(warn.getHostErrorCode() + EvolutionConstantUtils.COLON + warn.getErrorDescription());
            errorList.add(error);
        }));
        return errorList;
    }

    /**
     * This method return UK.OBIE error code for status
     *
     * @param status
     * @return ErrorCode
     */
    @MethodLogger
    public String getErrorCode(final HttpStatus status) {
        final String errorCode;
        switch (status) {
            case BAD_REQUEST:
                errorCode = ErrorCodes.BAD_REQUEST.toCode();
                break;
            case NOT_FOUND:
                errorCode = ErrorCodes.NOT_FOUND.toCode();
                break;
            case FORBIDDEN:
                errorCode = ErrorCodes.FORBIDDEN.toCode();
                break;
            case INTERNAL_SERVER_ERROR:
                errorCode = ErrorCodes.INTERNAL_SERVER_ERROR.toCode();
                break;
            case UNAUTHORIZED:
                errorCode = ErrorCodes.UNAUTHORIZED.toCode();
                break;
            case REQUEST_TIMEOUT:
                errorCode = ErrorCodes.REQUESTTIMEOUT.toCode();
                break;
            default:
                errorCode = ErrorCodes.UNEXPECTEDERROR.toCode();
                break;
        }
        return errorCode;
    }

    /**
     * This method return Http status for host error code
     *
     * @param hostCode
     * @return HttpStatus
     */
    @MethodLogger
    @SuppressWarnings("PMD")
    public HttpStatus getStatusFromHostCode(final String hostCode) {
        final Map<String, HttpStatus> hostCodeToStatusMap = new HashMap<>();
        final String[] badRequestErrorCodes = {
            HostErrorCodes.PS10032.toCode(), HostErrorCodes.PS10040.toCode(), HostErrorCodes.PS10187.toCode(),
            HostErrorCodes.CR60009.toCode(), HostErrorCodes.CR60080.toCode(), HostErrorCodes.CR60014.toCode(),
            HostErrorCodes.CR60018.toCode(), HostErrorCodes.CU00004.toCode(),
            HostErrorCodes.CU00005.toCode(), HostErrorCodes.CU00008.toCode(), HostErrorCodes.CU00026.toCode(),
            HostErrorCodes.CU00157.toCode(), HostErrorCodes.CU00451.toCode(),
            HostErrorCodes.CU00500.toCode(), HostErrorCodes.CU00506.toCode(), HostErrorCodes.CU00513.toCode(),
            HostErrorCodes.CU00520.toCode(), HostErrorCodes.CU00601.toCode(),
            HostErrorCodes.CU00610.toCode(), HostErrorCodes.CU00611.toCode(), HostErrorCodes.CU00612.toCode(),
            HostErrorCodes.CU00613.toCode(), HostErrorCodes.CU90023.toCode(),
            HostErrorCodes.EF90070.toCode(), HostErrorCodes.EF90092.toCode(),
            HostErrorCodes.CU00002.toCode(), HostErrorCodes.CU00007.toCode(), HostErrorCodes.CU00009.toCode(),
            HostErrorCodes.CU00010.toCode(), HostErrorCodes.CU00017.toCode(), HostErrorCodes.CU00021.toCode(),
            HostErrorCodes.CU00022.toCode(), HostErrorCodes.CU00025.toCode(),
            HostErrorCodes.CU00027.toCode(), HostErrorCodes.CU00028.toCode(), HostErrorCodes.CU00029.toCode(),
            HostErrorCodes.CU00030.toCode(), HostErrorCodes.CU00031.toCode(), HostErrorCodes.CU00070.toCode(),
            HostErrorCodes.CU00071.toCode(), HostErrorCodes.CU00072.toCode(), HostErrorCodes.CU00074.toCode(),
            HostErrorCodes.CU00075.toCode(), HostErrorCodes.CU00076.toCode(), HostErrorCodes.CU00077.toCode(),
            HostErrorCodes.CU00078.toCode(), HostErrorCodes.CU00080.toCode(), HostErrorCodes.CU00081.toCode(),
            HostErrorCodes.CU00082.toCode(), HostErrorCodes.CU00083.toCode(), HostErrorCodes.CU00084.toCode(),
            HostErrorCodes.CU00115.toCode(), HostErrorCodes.CU00158.toCode(),
            HostErrorCodes.CU00159.toCode(), HostErrorCodes.CU00195.toCode(), HostErrorCodes.CU00238.toCode(),
            HostErrorCodes.CU00242.toCode(), HostErrorCodes.CU00244.toCode(), HostErrorCodes.CU00245.toCode(),
            HostErrorCodes.CU00246.toCode(), HostErrorCodes.CU00249.toCode(), HostErrorCodes.CU00480.toCode(),
            HostErrorCodes.CU00481.toCode(), HostErrorCodes.CU00482.toCode(), HostErrorCodes.CU00483.toCode(),
            HostErrorCodes.CU00501.toCode(), HostErrorCodes.CU00505.toCode(),
            HostErrorCodes.CU00515.toCode(), HostErrorCodes.CU00521.toCode(), HostErrorCodes.CU00522.toCode(),
            HostErrorCodes.CU00524.toCode(), HostErrorCodes.CU00525.toCode(), HostErrorCodes.CU00526.toCode(),
            HostErrorCodes.CU00560.toCode(), HostErrorCodes.CU00600.toCode(), HostErrorCodes.CU00602.toCode(),
            HostErrorCodes.CU00607.toCode(), HostErrorCodes.CU00614.toCode(), HostErrorCodes.CU00618.toCode(),
            HostErrorCodes.CU00619.toCode(), HostErrorCodes.CU00620.toCode(), HostErrorCodes.CU00650.toCode(),
            HostErrorCodes.CU00677.toCode(), HostErrorCodes.CU00687.toCode(), HostErrorCodes.CU00688.toCode(),
            HostErrorCodes.CU00693.toCode(), HostErrorCodes.CU00694.toCode(), HostErrorCodes.CU00695.toCode(),
            HostErrorCodes.CU00696.toCode(), HostErrorCodes.CU00697.toCode(), HostErrorCodes.CU89008.toCode(),
            HostErrorCodes.CU89013.toCode(), HostErrorCodes.CU89014.toCode(), HostErrorCodes.CU90005.toCode(),
            HostErrorCodes.CU90006.toCode(), HostErrorCodes.CU90007.toCode(), HostErrorCodes.CU90008.toCode(),
            HostErrorCodes.CU90009.toCode(), HostErrorCodes.CU90010.toCode(), HostErrorCodes.CU90011.toCode(),
            HostErrorCodes.CU90012.toCode(), HostErrorCodes.CU99120.toCode(), HostErrorCodes.CU99129.toCode(),

        };

        Arrays.stream(badRequestErrorCodes)
            .forEach(errorCode -> hostCodeToStatusMap.put(errorCode, HttpStatus.BAD_REQUEST));

        hostCodeToStatusMap.put(HostErrorCodes.CR60012.toCode(), HttpStatus.UNAUTHORIZED);
        hostCodeToStatusMap.put(HostErrorCodes.HC00032.toCode(), HttpStatus.UNAUTHORIZED);
        hostCodeToStatusMap.put(HostErrorCodes.HC00033.toCode(), HttpStatus.UNAUTHORIZED);
        hostCodeToStatusMap.put(HostErrorCodes.CR60016.toCode(), HttpStatus.CONFLICT);
        hostCodeToStatusMap.put(HostErrorCodes.CR60019.toCode(), HttpStatus.CONFLICT);
        hostCodeToStatusMap.put(HostErrorCodes.CR60015.toCode(), HttpStatus.NOT_FOUND);
        hostCodeToStatusMap.put(HostErrorCodes.CR60017.toCode(), HttpStatus.NOT_FOUND);
        hostCodeToStatusMap.put(HostErrorCodes.CU00542.toCode(), HttpStatus.FORBIDDEN);
        hostCodeToStatusMap.put(HostErrorCodes.CR60066.toCode(), HttpStatus.FORBIDDEN);
        hostCodeToStatusMap.put(HostErrorCodes.HC00034.toCode(), HttpStatus.REQUEST_TIMEOUT);
        hostCodeToStatusMap.put(HostErrorCodes.CR60013.toCode(), HttpStatus.REQUEST_TIMEOUT);
        hostCodeToStatusMap.put(HostErrorCodes.HC00052.toCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        hostCodeToStatusMap.put(HostErrorCodes.HC00331.toCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        hostCodeToStatusMap.put(HostErrorCodes.CR60008.toCode(), HttpStatus.INTERNAL_SERVER_ERROR);

        return hostCodeToStatusMap.getOrDefault(hostCode, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method return host code from status
     *
     * @param status
     * @return HostErrorCode
     */
    @MethodLogger
    public String getHostCode(final StatusType status) {
        String hostErrorCode = EvolutionConstantUtils.EMPTY_STRING;
        if (Objects.nonNull(status.getErrors()) && !status.getErrors().isEmpty()) {
            hostErrorCode = status.getErrors().get(EvolutionConstantUtils.ZERO).getHostErrorCode();
        } else if (Objects.nonNull(status.getWarnings()) && !status.getWarnings().isEmpty()) {
            hostErrorCode = status.getWarnings().get(EvolutionConstantUtils.ZERO).getHostErrorCode();
        }
        return hostErrorCode;
    }

}

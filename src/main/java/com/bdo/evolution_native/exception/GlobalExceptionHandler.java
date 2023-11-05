package com.bdo.evolution_native.exception;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.bdo.evolution_native.enums.ErrorCodes;
import com.bdo.evolution_native.model.ErrorResponse;
import com.bdo.evolution_native.util.ExceptionHandlerUtil;
import com.bdo.evolution_native.util.MethodLogger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;

/**
 * This class is handling all exceptions
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ExceptionHandlerUtil exceptionUtil;

    /**
     * This method handeling bad request
     *
     * @param ex
     * @param request
     * @return ErrorResponse
     */
    @MethodLogger
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
        MissingServletRequestParameterException.class,
        ServletRequestBindingException.class,
        TypeMismatchException.class,
        HttpMessageNotReadableException.class,
        MethodArgumentNotValidException.class,
        MissingServletRequestPartException.class,
        BindException.class,
        ConstraintViolationException.class,
        UseCaseNotValidException.class,
        RecordNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequestExceptions(
        final Exception ex, final HttpServletRequest request) {
        final ErrorResponse errorResponse = exceptionUtil.buildErrorResponse(
            "[BadRequestException] Handler: {} ErrorCode: {}, Message: {}",
            ex, request, HttpStatus.BAD_REQUEST);

        if (ex instanceof TypeMismatchException) {
            errorResponse.setMessage(EvolutionConstantUtils.TYPEMISMATCH_ERROR + EvolutionConstantUtils.SPACE
                + ErrorCodes.BAD_REQUEST.toCode());
            errorResponse.getErrors().forEach(error -> error
                .setMessage(EvolutionConstantUtils.TYPEMISMATCH_ERROR));
        } else if (ex instanceof HttpMessageNotReadableException) {
            errorResponse.setMessage(EvolutionConstantUtils.INVALID_HTTP_MESSAGE + EvolutionConstantUtils.SPACE
                + ErrorCodes.BAD_REQUEST.toCode());
            errorResponse.getErrors().forEach(error -> error
                .setMessage(EvolutionConstantUtils.INVALID_HTTP_MESSAGE));
        }
        errorResponse.getErrors().forEach(error -> error
            .setErrorCode(ErrorCodes.BAD_REQUEST.toCode()));
        return new ResponseEntity<>(errorResponse, exceptionUtil.getHeaders(request), HttpStatus.BAD_REQUEST);
    }

    /**
     * This method is handling NOT_FOUND exceptions
     *
     * @param ex
     * @param request
     * @return ResponseEntity<ErrorResponse>
     */
    @MethodLogger
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(final Exception ex, final HttpServletRequest request) {
        final ErrorResponse errorResponse = exceptionUtil.buildErrorResponse(
            "[404-Resource not found] Handler: {} ErrorCode: {}, Message: {}", ex,
            request, HttpStatus.NOT_FOUND);
        errorResponse.setMessage(EvolutionConstantUtils.PATH_NOT_FOUND + EvolutionConstantUtils.SPACE
            + ErrorCodes.NOT_FOUND.toCode());
        errorResponse.getErrors().forEach(error -> {
            error.setMessage(EvolutionConstantUtils.PATH_NOT_FOUND);
            error.setErrorCode(ErrorCodes.NOT_FOUND.toCode());
        });
        return new ResponseEntity<>(errorResponse, exceptionUtil.getHeaders(request), HttpStatus.NOT_FOUND);
    }

    /**
     * This method is handling Forbidden status
     *
     * @param ex
     * @param request
     * @return ResponseEntity<ErrorResponse>
     */
    @MethodLogger
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleForbidden(final Exception ex, final HttpServletRequest request) {
        final ErrorResponse errorResponse = exceptionUtil.buildErrorResponse(
            "[Forbidden] Handler: {} ErrorCode: {}, Message: {}", ex,
            request, HttpStatus.FORBIDDEN);
        errorResponse.setMessage(EvolutionConstantUtils.ACCESS_DENIED + EvolutionConstantUtils.SPACE
            + ErrorCodes.FORBIDDEN.toCode());
        errorResponse.getErrors().forEach(error -> {
            error.setMessage(EvolutionConstantUtils.ACCESS_DENIED);
            error.setErrorCode(ErrorCodes.FORBIDDEN.toCode());
        });
        return new ResponseEntity<>(errorResponse, exceptionUtil.getHeaders(request), HttpStatus.FORBIDDEN);
    }

    /**
     * This method is handling unauthorized response
     *
     * @param ex
     * @param request
     * @return ResponseEntity<ErrorResponse>
     */
    @MethodLogger
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(final Exception ex, final HttpServletRequest request) {
        exceptionUtil.printLogTrace(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(exceptionUtil.getHeaders(request)).build();
    }

    /**
     * This method is handling Methods not allowed errors
     *
     * @param ex
     * @param request
     * @return ResponseEntity<ErrorResponse>
     */
    @MethodLogger
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotAllowed(final Exception ex, final HttpServletRequest request) {
        exceptionUtil.printLogTrace(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).headers(exceptionUtil.getHeaders(request)).build();
    }

    /**
     * This method is handling Not acceptable errors
     *
     * @param ex
     * @param request
     * @return ResponseEntity<ErrorResponse>
     */
    @MethodLogger
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class,
        HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<ErrorResponse> handleNotAcceptable(final Exception ex, final HttpServletRequest request) {
        exceptionUtil.printLogTrace(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).headers(exceptionUtil.getHeaders(request)).build();
    }

    /**
     * This method is handling Unprocessable Entity errors
     *
     * @param ex
     * @param request
     * @return ErrorResponse
     */
    @MethodLogger
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(HttpClientErrorException.UnprocessableEntity.class)
    public ResponseEntity<ErrorResponse> handleUnproccessableEntity(final Exception ex,
                                                                    final HttpServletRequest request) {

        final ErrorResponse errorResponse = exceptionUtil.buildErrorResponse(
            "[Unprocessable Entity] Handler: {} ErrorCode: {}, Message: {}", ex,
            request, HttpStatus.UNPROCESSABLE_ENTITY);
        errorResponse.setMessage(EvolutionConstantUtils.UNPROCESSABLE_ENTITY_ERROR + EvolutionConstantUtils.SPACE
            + ErrorCodes.UNPROCESSABLE_ENTITY.toCode());
        final HttpHeaders headers = exceptionUtil.getHeaders(request);
        headers.add(EvolutionConstantUtils.RETRY_AFTER_HEADER,
            String.valueOf(EvolutionConstantUtils.RETRY_AFTER_VALUE));
        errorResponse.getErrors().forEach(error -> {
            error.setMessage(EvolutionConstantUtils.UNPROCESSABLE_ENTITY_ERROR);
            error.setErrorCode(ErrorCodes.UNPROCESSABLE_ENTITY.toCode());
        });
        return new ResponseEntity<>(errorResponse, headers, HttpStatus.UNPROCESSABLE_ENTITY);

    }

    /**
     * This method is handling too many request errors
     *
     * @param ex
     * @param request
     * @return ErrorResponse
     */
    @MethodLogger
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    @ExceptionHandler(HttpClientErrorException.TooManyRequests.class)
    public ResponseEntity<ErrorResponse> handleTooManyRequest(final Exception ex, final HttpServletRequest request) {
        final HttpHeaders headers = exceptionUtil.getHeaders(request);
        headers.add(EvolutionConstantUtils.RETRY_AFTER_HEADER,
            String.valueOf(EvolutionConstantUtils.RETRY_AFTER_VALUE));
        exceptionUtil.printLogTrace(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).headers(headers).build();
    }

    /**
     * This method handles error from SOR
     *
     * @param ex
     * @param request
     * @return ErrorResponse
     */
    @MethodLogger
    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ErrorResponse> handleWebClientException(final ClientException ex,
                                                                  final HttpServletRequest request) {
        final ResponseEntity<ErrorResponse> result;
        if (ex.getStatus() < 0) {
            final ErrorResponse errorResponse = exceptionUtil.buildErrorResponse(
                "[SOR Connection Error] Handler: {} ErrorCode: {}, Message: {}", ex,
                request, HttpStatus.BAD_GATEWAY);
            errorResponse.setMessage(EvolutionConstantUtils.SOR_CONNECT_ERROR + EvolutionConstantUtils.SPACE
                + ErrorCodes.BAD_GATEWAY.toCode());
            errorResponse.getErrors().forEach(error -> {
                error.setMessage(EvolutionConstantUtils.SOR_CONNECT_ERROR);
                error.setErrorCode(ErrorCodes.BAD_GATEWAY.toCode());
            });
            result = new ResponseEntity<>(errorResponse, exceptionUtil.getHeaders(request), HttpStatus.BAD_GATEWAY);
        } else {
            final ErrorResponse errorResponse = exceptionUtil.buildErrorResponse(
                "[SOR Error] Handler: {} ErrorCode: {}, Message: {}", ex,
                request, HttpStatus.valueOf(ex.getStatus()));
            final String message = exceptionUtil.getClientMessage(ex.getStatus());
            errorResponse.setMessage(message);
            errorResponse.getErrors().forEach(error -> {
                error.setMessage(message.substring(0, message.indexOf(EvolutionConstantUtils.ERROR_CODE_NAME)).trim());
                error.setErrorCode(message.substring(message.indexOf(EvolutionConstantUtils.ERROR_CODE_NAME)).trim());
            });
            result = new ResponseEntity<>(errorResponse, exceptionUtil.getHeaders(request), ex.getStatus());
        }
        return result;
    }

    /**
     * This method handles error from Authorization API
     *
     * @param tokenClientException
     * @param request
     * @return ErrorResponse
     */
    @MethodLogger
    @ExceptionHandler(TokenClientException.class)
    public ResponseEntity<ErrorResponse> handleAuthorizationApiException(
        final TokenClientException tokenClientException, final HttpServletRequest request) {
        final ResponseEntity<ErrorResponse> errorResult;
        if (tokenClientException.getStatus() < 0) {
            final ErrorResponse errorResponse = exceptionUtil.buildErrorResponse(
                "[Authorization API Connection Error] Handler: {} ErrorCode: {}, Message: {}", tokenClientException,
                request, HttpStatus.BAD_GATEWAY);
            errorResponse.setMessage(EvolutionConstantUtils.OAUTH_API_CONNECT_ERROR + EvolutionConstantUtils.SPACE
                + ErrorCodes.BAD_GATEWAY.toCode());
            errorResponse.getErrors().forEach(error -> {
                error.setMessage(EvolutionConstantUtils.OAUTH_API_CONNECT_ERROR);
                error.setErrorCode(ErrorCodes.BAD_GATEWAY.toCode());
            });
            errorResult = new ResponseEntity<>(errorResponse,
                exceptionUtil.getHeaders(request), HttpStatus.BAD_GATEWAY);
        } else {
            final ErrorResponse errorResponses = exceptionUtil.buildErrorResponse(
                "[Authorization API Error] Handler: {} ErrorCode: {}, Message: {}", tokenClientException,
                request, HttpStatus.valueOf(tokenClientException.getStatus()));
            final String message = exceptionUtil.getOauthClientMessage(tokenClientException.getStatus());
            errorResponses.setMessage(message);
            errorResponses.getErrors().forEach(error -> {
                error.setMessage(message.substring(0, message.indexOf(EvolutionConstantUtils.ERROR_CODE_NAME)).trim());
                error.setErrorCode(message.substring(message.indexOf(EvolutionConstantUtils.ERROR_CODE_NAME)).trim());
            });
            errorResult = new ResponseEntity<>(errorResponses, exceptionUtil.getHeaders(request),
                tokenClientException.getStatus());
        }
        return errorResult;
    }

    /**
     * This method is handling System Api errors
     *
     * @param ex
     * @param request
     * @return ResponseEntity<ErrorResponse>
     */
    @MethodLogger
    @ExceptionHandler(SystemApiError.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(final Exception ex,
                                                                   final HttpServletRequest request) {
        final ErrorResponse errorResponse = exceptionUtil.buildErrorResponse(
            "[Internal Server error] Handler: {} ErrorCode: {}, Message: {}", ex,
            request, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse, exceptionUtil.getHeaders(request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method handle global exception
     *
     * @param ex
     * @param request
     * @return ErrorResponse
     */
    @MethodLogger
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(final Exception ex, final HttpServletRequest request) {
        final ErrorResponse errorResponse = exceptionUtil.buildErrorResponse(
            "[Internal Server error] Handler: {} ErrorCode: {}, Message: {}", ex,
            request, HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setMessage(EvolutionConstantUtils.ANY_ERRORS + EvolutionConstantUtils.SPACE
            + ErrorCodes.UNEXPECTEDERROR.toCode());
        errorResponse.getErrors().forEach(error -> error.setMessage(EvolutionConstantUtils.ANY_ERRORS));
        return new ResponseEntity<>(errorResponse, exceptionUtil.getHeaders(request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method handeling EvolutionException
     *
     * @param ex
     * @param request
     * @return ErrorResponse
     */
    @MethodLogger
    @ExceptionHandler(EvolutionException.class)
    public ResponseEntity<ErrorResponse> handleEvolutionException(final EvolutionException ex,
                                                                  final HttpServletRequest request) {
        final String hostCode = exceptionUtil.getHostCode(ex.getStatus());
        final HttpStatus status = exceptionUtil.getStatusFromHostCode(hostCode);
        final ErrorResponse errorResponse = exceptionUtil.errorResponse(
            "[" + status + "] Handler: {} ErrorCode: {}, Message: {}", ex,
            request, status);
        return new ResponseEntity<>(errorResponse, exceptionUtil.getHeaders(request), status);
    }

}

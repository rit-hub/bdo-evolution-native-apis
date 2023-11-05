package com.bdo.evolution_native.exception;

import com.bdo.evolution_native.client.model.StatusType;
import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.bdo.evolution_native.enums.ErrorCodes;
import com.bdo.evolution_native.model.Error;
import com.bdo.evolution_native.model.ErrorResponse;
import com.bdo.evolution_native.util.ExceptionHandlerUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * The type Global exception handler test.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@SpringBootTest
class GlobalExceptionHandlerTest {
    @Mock
    private ExceptionHandlerUtil exceptionUtil;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    /**
     * Handle resource not found.
     */
    @Test
        void handleResourceNotFound() {
            HttpServletRequest http = mock(HttpServletRequest.class);
            ErrorResponse errorResponse = new ErrorResponse();
            Error error = new Error();
            errorResponse.addError(error);
            when(exceptionUtil.buildErrorResponse(anyString(),any(Exception.class),any(HttpServletRequest.class),any(HttpStatus.class))).thenReturn(errorResponse);
            NoHandlerFoundException runtimeException = new NoHandlerFoundException("ResourceNotFound", "request/123", null);
            ResponseEntity<ErrorResponse> result = globalExceptionHandler.handleResourceNotFound(runtimeException, http);
            assertEquals("Invalid Request Path UK.OBIE.NotFound", result.getBody().getMessage());
            Error errors = result.getBody().getErrors().get(0);
            assertEquals(EvolutionConstantUtils.PATH_NOT_FOUND, errors.getMessage());
            assertEquals(ErrorCodes.NOT_FOUND.toCode(), errors.getErrorCode());
        }


    /**
     * Handle forbidden.
     */
    @Test
        void handleForbidden() {
            HttpServletRequest http = mock(HttpServletRequest.class);
            ErrorResponse errorResponse = new ErrorResponse();
            Error error = new Error();
            errorResponse.addError(error);
            when(exceptionUtil.buildErrorResponse(anyString(),any(Exception.class),any(HttpServletRequest.class),any(HttpStatus.class))).thenReturn(errorResponse);
            AccessDeniedException runtimeException = new AccessDeniedException("AccessDenied", "request/123", null);
            ResponseEntity<ErrorResponse> result = globalExceptionHandler.handleForbidden(runtimeException, http);
            assertEquals("Access Denied UK.OBIE.Forbidden", result.getBody().getMessage());

            Error errors = result.getBody().getErrors().get(0);
            assertEquals(EvolutionConstantUtils.ACCESS_DENIED, errors.getMessage());
            assertEquals(ErrorCodes.FORBIDDEN.toCode(), errors.getErrorCode());

        }


    /**
     * Handle unauthorized.
     */
    @Test
        void handleUnauthorized() {
            HttpServletRequest http = mock(HttpServletRequest.class);
            when(exceptionUtil.buildErrorResponse(anyString(),any(Exception.class),any(HttpServletRequest.class),any(HttpStatus.class))).thenReturn(new ErrorResponse());
            AuthenticationException runtimeException = new AuthenticationException("Auth error");
            ResponseEntity<ErrorResponse> result = globalExceptionHandler.handleUnauthorized(runtimeException, http);
            assertEquals("401 UNAUTHORIZED", result.getStatusCode().toString());
        }

    /**
     * Handle internal server error test.
     */
    @Test
    void handleInternalServerErrorTest() {
        HttpServletRequest http = mock(HttpServletRequest.class);
        when(exceptionUtil.buildErrorResponse(anyString(),any(Exception.class),any(HttpServletRequest.class),any(HttpStatus.class))).thenReturn(new ErrorResponse());
        SystemApiError runtimeException = new SystemApiError("Auth error");
        ResponseEntity<ErrorResponse> result = globalExceptionHandler.handleInternalServerError(runtimeException, http);
        assertEquals("500 INTERNAL_SERVER_ERROR", result.getStatusCode().toString());
    }

    /**
     * Handle bad request exception test.
     */
    @Test
    void handleBadRequestExceptionTest() {
        HttpServletRequest http = mock(HttpServletRequest.class);
        ErrorResponse errorResponse = new ErrorResponse();
        Error error = new Error();
        errorResponse.addError(error);
        when(exceptionUtil.buildErrorResponse(anyString(),any(Exception.class),any(HttpServletRequest.class),any(HttpStatus.class))).thenReturn(new ErrorResponse());
        RecordNotFoundException runtimeException = new RecordNotFoundException("",new Throwable());
        ResponseEntity<ErrorResponse> result = globalExceptionHandler.handleBadRequestExceptions(runtimeException, http);
        assertEquals("400 BAD_REQUEST", result.getStatusCode().toString());
    }

    /**
     * Handle bad request exception 1 test.
     */
    @Test
    void handleBadRequestException1Test() {
        HttpServletRequest http = mock(HttpServletRequest.class);
        ErrorResponse errorResponse = new ErrorResponse();
        Error error = new Error();
        errorResponse.addError(error);
        when(exceptionUtil.buildErrorResponse(anyString(),any(Exception.class),any(HttpServletRequest.class),any(HttpStatus.class))).thenReturn(errorResponse);
        HttpMessageNotReadableException runtimeException= new HttpMessageNotReadableException("");
        ResponseEntity<ErrorResponse> result = globalExceptionHandler.handleBadRequestExceptions(runtimeException, http);
        assertEquals("400 BAD_REQUEST", result.getStatusCode().toString());
    }

    /**
     * Handle bad request exception 2 test.
     */
    @Test
    void handleBadRequestException2Test() {
        HttpServletRequest http = mock(HttpServletRequest.class);
        ErrorResponse errorResponse = new ErrorResponse();
        Error error = new Error();
        errorResponse.addError(error);
        when(exceptionUtil.buildErrorResponse(anyString(),any(Exception.class),any(HttpServletRequest.class),any(HttpStatus.class))).thenReturn(errorResponse);
        Exception runtimeException= new TypeMismatchException("", "".getClass());
        ResponseEntity<ErrorResponse> result = globalExceptionHandler.handleBadRequestExceptions(runtimeException, http);
        assertEquals("400 BAD_REQUEST", result.getStatusCode().toString());
    }

    /**
     * Handle unprocessable entity test.
     */
    @Test
    void handleUnprocessableEntityTest() {
        HttpServletRequest http = mock(HttpServletRequest.class);
        ErrorResponse errorResponse = new ErrorResponse();
        Error error = new Error();
        errorResponse.addError(error);
        when(exceptionUtil.buildErrorResponse(anyString(),any(Exception.class),any(HttpServletRequest.class),any(HttpStatus.class))).thenReturn(errorResponse);
        Exception runtimeException = new Exception("",new Throwable());
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders(header);
        when(exceptionUtil.getHeaders(http)).thenReturn(headers);
        ResponseEntity<ErrorResponse> result = globalExceptionHandler.handleUnproccessableEntity(runtimeException, http);
        assertEquals("422 UNPROCESSABLE_ENTITY", result.getStatusCode().toString());
        Error errors = result.getBody().getErrors().get(0);
        assertEquals(EvolutionConstantUtils.UNPROCESSABLE_ENTITY_ERROR, errors.getMessage());
        assertEquals(ErrorCodes.UNPROCESSABLE_ENTITY.toCode(), errors.getErrorCode());
    }

    /**
     * Handle global exception test.
     */
    @Test
    void handleGlobalExceptionTest() {
        HttpServletRequest http = mock(HttpServletRequest.class);
        ErrorResponse errorResponse = new ErrorResponse();
        Error error = new Error();
        errorResponse.addError(error);
        when(exceptionUtil.buildErrorResponse(anyString(),any(Exception.class),any(HttpServletRequest.class),any(HttpStatus.class))).thenReturn(errorResponse);
        Exception runtimeException = new Exception("Auth error");
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders(header);
        when(exceptionUtil.getHeaders(http)).thenReturn(headers);
        ResponseEntity<ErrorResponse> result = globalExceptionHandler.handleGlobalException(runtimeException, http);
        assertEquals("500 INTERNAL_SERVER_ERROR", result.getStatusCode().toString());
        Error errors = result.getBody().getErrors().get(0);
        assertEquals(EvolutionConstantUtils.ANY_ERRORS, errors.getMessage());
    }

    /**
     * Handle web client exception test.
     */
    @Test
    void handleWebClientExceptionTest() {
        ErrorResponse errorResponse = new ErrorResponse();
        Error error = new Error();
        errorResponse.addError(error);
        HttpServletRequest http = mock(HttpServletRequest.class);
        ClientException runtimeException = new ClientException(-1,"bad gateway");
        when(exceptionUtil.buildErrorResponse(anyString(),any(Exception.class),any(HttpServletRequest.class),any(HttpStatus.class))).thenReturn(errorResponse);
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders(header);
        when(exceptionUtil.getHeaders(http)).thenReturn(headers);
        ResponseEntity<ErrorResponse> result = globalExceptionHandler.handleWebClientException(runtimeException, http);
        assertEquals("502 BAD_GATEWAY", result.getStatusCode().toString());
        Error errors = result.getBody().getErrors().get(0);
        assertEquals(EvolutionConstantUtils.SOR_CONNECT_ERROR, errors.getMessage());
        assertEquals(ErrorCodes.BAD_GATEWAY.toCode(), errors.getErrorCode());
    }

    /**
     * Handle web client exception 1 test.
     */
    @Test
    void handleWebClientException1Test() {
        ErrorResponse errorResponse = new ErrorResponse();
        HttpServletRequest http = mock(HttpServletRequest.class);
        ClientException runtimeException = new ClientException(400,"Bad Request");
        when(exceptionUtil.getClientMessage(400)).thenReturn("BAD_REQUEST");
        when(exceptionUtil.buildErrorResponse(anyString(),any(Exception.class),any(HttpServletRequest.class),any(HttpStatus.class))).thenReturn(errorResponse);
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders(header);
        when(exceptionUtil.getHeaders(http)).thenReturn(headers);
        ResponseEntity<ErrorResponse> result = globalExceptionHandler.handleWebClientException(runtimeException, http);
        assertEquals("400 BAD_REQUEST", result.getStatusCode().toString());
    }
    @Test
    void handleAuthorizationApiExceptionTest() {
        ErrorResponse errorResponse = new ErrorResponse();
        Error error = new Error();
        errorResponse.addError(error);
        HttpServletRequest http = mock(HttpServletRequest.class);
        TokenClientException runtimeException = new TokenClientException(-1,"bad gateway");
        when(exceptionUtil.buildErrorResponse(anyString(),any(Exception.class),any(HttpServletRequest.class),any(HttpStatus.class))).thenReturn(errorResponse);
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders(header);
        when(exceptionUtil.getHeaders(http)).thenReturn(headers);
        ResponseEntity<ErrorResponse> result = globalExceptionHandler.handleAuthorizationApiException(runtimeException, http);
        assertEquals("502 BAD_GATEWAY", result.getStatusCode().toString());
        Error errors = result.getBody().getErrors().get(0);
        assertEquals(EvolutionConstantUtils.OAUTH_API_CONNECT_ERROR, errors.getMessage());
        assertEquals(ErrorCodes.BAD_GATEWAY.toCode(), errors.getErrorCode());
    }

    /**
     * Handle web client exception 1 test.
     */
    @Test
    void handleAuthorizationApiExceptionTest1() {
        ErrorResponse errorResponse = new ErrorResponse();
        HttpServletRequest http = mock(HttpServletRequest.class);
        TokenClientException runtimeException = new TokenClientException(400,"Bad Request");
        when(exceptionUtil.getOauthClientMessage(400)).thenReturn("BAD_REQUEST");
        when(exceptionUtil.buildErrorResponse(anyString(),any(Exception.class),any(HttpServletRequest.class),any(HttpStatus.class))).thenReturn(errorResponse);
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders(header);
        when(exceptionUtil.getHeaders(http)).thenReturn(headers);
        ResponseEntity<ErrorResponse> result = globalExceptionHandler.handleAuthorizationApiException(runtimeException, http);
        assertEquals("400 BAD_REQUEST", result.getStatusCode().toString());
    }


    /**
     * Handle evolution exception test.
     */
    @Test
    void handleEvolutionExceptionTest() {
        HttpServletRequest http = mock(HttpServletRequest.class);
        StatusType statusType= new StatusType();
        when(exceptionUtil.getHostCode(any(StatusType.class))).thenReturn("status");
        when(exceptionUtil.getStatusFromHostCode(anyString())).thenReturn(HttpStatus.valueOf("BAD_REQUEST"));
        when(exceptionUtil.errorResponse(anyString(),any(EvolutionException.class),any(HttpServletRequest.class),any(HttpStatus.class))).thenReturn(new ErrorResponse());
        EvolutionException runtimeException = new EvolutionException(statusType);
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders(header);
        when(exceptionUtil.getHeaders(http)).thenReturn(headers);
        ResponseEntity<ErrorResponse> result = globalExceptionHandler.handleEvolutionException(runtimeException, http);
        assertEquals("400 BAD_REQUEST", result.getStatusCode().toString());
    }


    /**
     * Handle method not allowed.
     */
    @Test
        void handleMethodNotAllowed() {
            HttpServletRequest http = mock(HttpServletRequest.class);
            when(exceptionUtil.buildErrorResponse(anyString(),any(Exception.class),any(HttpServletRequest.class),any(HttpStatus.class))).thenReturn(new ErrorResponse());
            HttpMediaTypeNotSupportedException runtimeException = new HttpMediaTypeNotSupportedException("Method not supported");
            Object result = globalExceptionHandler.handleMethodNotAllowed(runtimeException, http);
            assertEquals("<405 METHOD_NOT_ALLOWED Method Not Allowed,[]>", result.toString());

        }


    /**
     * Handle not acceptable http media type not acceptable exception.
     */
    @Test
        void handleNotAcceptable_HttpMediaTypeNotAcceptableException() {
            when(exceptionUtil.buildErrorResponse(anyString(),any(Exception.class),any(HttpServletRequest.class),any(HttpStatus.class))).thenReturn(new ErrorResponse());
            HttpServletRequest http = mock(HttpServletRequest.class);
            HttpMediaTypeNotAcceptableException runtimeException = new HttpMediaTypeNotAcceptableException("Method not acceptable");
            ResponseEntity<ErrorResponse> result = globalExceptionHandler.handleNotAcceptable(runtimeException, http);
            assertEquals("406 NOT_ACCEPTABLE", result.getStatusCode().toString());

        }

    /**
     * Handle not acceptable http media type not supported exception.
     */
    @Test
        void handleNotAcceptable_HttpMediaTypeNotSupportedException() {
            when(exceptionUtil.buildErrorResponse(anyString(),any(Exception.class),any(HttpServletRequest.class),any(HttpStatus.class))).thenReturn(new ErrorResponse());
            HttpServletRequest http = mock(HttpServletRequest.class);
            HttpMediaTypeNotSupportedException runtimeException = new HttpMediaTypeNotSupportedException("Method not acceptable");
            ResponseEntity<ErrorResponse> result = globalExceptionHandler.handleNotAcceptable(runtimeException, http);
            assertEquals("406 NOT_ACCEPTABLE", result.getStatusCode().toString());

        }

    /**
     * Handletoo many test.
     */
    @Test
    void handletooManyTest() {
        StringBuffer url = new StringBuffer("request/123");
        HttpServletRequest http = mock(HttpServletRequest.class);
        doReturn(url).when(http).getRequestURL();
        HttpClientErrorException runtimeException = new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY);
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders(header);
        when(exceptionUtil.getHeaders(http)).thenReturn(headers);
        doNothing().when(exceptionUtil).printLogTrace(anyString(),any(Exception.class));
        ResponseEntity<ErrorResponse> result = globalExceptionHandler.handleTooManyRequest(runtimeException, http);
        assertEquals("429 TOO_MANY_REQUESTS", result.getStatusCode().toString());
    }





}





package com.bdo.evolution_native.util;

import com.bdo.evolution_native.client.model.ErrorType;
import com.bdo.evolution_native.client.model.StatusType;
import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.bdo.evolution_native.enums.ErrorCodes;
import com.bdo.evolution_native.enums.HostErrorCodes;
import com.bdo.evolution_native.model.ErrorResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The type Exception handler util test.
 */
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ExceptionHandlerUtilTest {
    @InjectMocks
    private ExceptionHandlerUtil exceptionHandlerUtil;
    private PrintStream originalSystemOut;
    private ByteArrayOutputStream outputStream;

    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() {
        originalSystemOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Tear down.
     */
    @AfterEach
    public void tearDown() {
        System.setOut(originalSystemOut);
    }


    /**
     * Test get status from host code.
     */
    @Test
     void testGetStatusFromHostCode() {

        assertEquals(HttpStatus.BAD_REQUEST, exceptionHandlerUtil.getStatusFromHostCode(HostErrorCodes.CR60009.toCode()));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exceptionHandlerUtil.getStatusFromHostCode(HostErrorCodes.HC00331.toCode()));
        assertEquals(HttpStatus.UNAUTHORIZED, exceptionHandlerUtil.getStatusFromHostCode(HostErrorCodes.CR60012.toCode()));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exceptionHandlerUtil.getStatusFromHostCode("UnknownHostCode"));
    }

    /**
     * Test get error code.
     */
    @Test
     void testGetErrorCode() {

        assertEquals(ErrorCodes.BAD_REQUEST.toCode(), exceptionHandlerUtil.getErrorCode(HttpStatus.BAD_REQUEST));
        assertEquals(ErrorCodes.NOT_FOUND.toCode(), exceptionHandlerUtil.getErrorCode(HttpStatus.NOT_FOUND));
        assertEquals(ErrorCodes.FORBIDDEN.toCode(), exceptionHandlerUtil.getErrorCode(HttpStatus.FORBIDDEN));
        assertEquals(ErrorCodes.INTERNAL_SERVER_ERROR.toCode(), exceptionHandlerUtil.getErrorCode(HttpStatus.INTERNAL_SERVER_ERROR));
        assertEquals(ErrorCodes.UNAUTHORIZED.toCode(), exceptionHandlerUtil.getErrorCode(HttpStatus.UNAUTHORIZED));
        assertEquals(ErrorCodes.REQUESTTIMEOUT.toCode(), exceptionHandlerUtil.getErrorCode(HttpStatus.REQUEST_TIMEOUT));
        assertEquals(ErrorCodes.UNEXPECTEDERROR.toCode(), exceptionHandlerUtil.getErrorCode(HttpStatus.GATEWAY_TIMEOUT)); // Default case
    }

    /**
     * Test get client message.
     */
    @Test
     void testGetClientMessage() {

        assertEquals(EvolutionConstantUtils.SOR_INVALID_REQUEST + EvolutionConstantUtils.SPACE + ErrorCodes.BAD_REQUEST.toCode(), exceptionHandlerUtil.getClientMessage(HttpStatus.BAD_REQUEST.value()));
        assertEquals(EvolutionConstantUtils.SOR_NOT_FOUND + EvolutionConstantUtils.SPACE + ErrorCodes.NOT_FOUND.toCode(), exceptionHandlerUtil.getClientMessage(HttpStatus.NOT_FOUND.value()));
        assertEquals(EvolutionConstantUtils.SOR_ACCESS_DENIED + EvolutionConstantUtils.SPACE + ErrorCodes.FORBIDDEN.toCode(), exceptionHandlerUtil.getClientMessage(HttpStatus.FORBIDDEN.value()));
        assertEquals(EvolutionConstantUtils.SOR_SERVER_ERROR + EvolutionConstantUtils.SPACE + ErrorCodes.INTERNAL_SERVER_ERROR.toCode(), exceptionHandlerUtil.getClientMessage(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        assertEquals(EvolutionConstantUtils.SOR_UNAUTHORIZED + EvolutionConstantUtils.SPACE + ErrorCodes.UNAUTHORIZED.toCode(), exceptionHandlerUtil.getClientMessage(HttpStatus.UNAUTHORIZED.value()));
        assertEquals(EvolutionConstantUtils.SOR_TIMEOUT + EvolutionConstantUtils.SPACE + ErrorCodes.REQUESTTIMEOUT.toCode(), exceptionHandlerUtil.getClientMessage(HttpStatus.REQUEST_TIMEOUT.value()));
        assertEquals("502" + EvolutionConstantUtils.SPACE + EvolutionConstantUtils.SOR_ANY_ERROR + EvolutionConstantUtils.SPACE + ErrorCodes.UNEXPECTEDERROR.toCode(), exceptionHandlerUtil.getClientMessage(502)); // Default case
    }

    /**
     * Test get headers.
     */
    @Test
     void testGetHeaders() {
        // Create a mock HttpServletRequest
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader(EvolutionConstantUtils.INTERACTION_ID_HEADER)).thenReturn("interactionIdValue");

        // Create an instance of the class containing the getHeaders method

        // Call the method
        HttpHeaders headers = exceptionHandlerUtil.getHeaders(request);

        // Assert the expected values
        assertEquals("application/json;charset=utf-8", headers.getContentType().toString().toLowerCase());
        assertEquals("interactionIdValue", headers.getFirst(EvolutionConstantUtils.INTERACTION_ID_HEADER));
    }

    /**
     * Test build error response bind exception.
     */
    @Test
     void testBuildErrorResponse_BindException() {
        // Create a mock BindException
        BindException ex = mock(BindException.class);
        when(ex.getMessage()).thenReturn("BindException message");

        // Create a mock HttpServletRequest
        HttpServletRequest request = mock(HttpServletRequest.class);

        // Create an instance of the class containing the buildErrorResponse method

        // Call the method
        ErrorResponse errorResponse = exceptionHandlerUtil.buildErrorResponse("errorType", ex, request, HttpStatus.BAD_REQUEST);

        // Assert the expected behavior for a BindException
        assertEquals("M.EVOLUTION.400", errorResponse.getCode());
        assertEquals("Validation Failed", errorResponse.getMessage());
        // Add more assertions as needed
    }

    /**
     * Test get host code with errors.
     */
    @Test
     void testGetHostCodeWithErrors() {
        // Create a StatusType instance with errors
        StatusType status = new StatusType();
        List<ErrorType> errors = new ArrayList<>();
        ErrorType error = new ErrorType();
        error.setHostErrorCode("ERROR123");
        errors.add(error);
        status.setErrors(errors);

        // Create an instance of your class (the class containing getHostCode method)

        // Call the getHostCode method
        String result = exceptionHandlerUtil.getHostCode(status);

        // Verify that the result is "ERROR123"
        assertEquals("ERROR123", result);
    }

    /**
     * Test get host code with warnings.
     */
    @Test
     void testGetHostCodeWithWarnings() {
        // Create a StatusType instance with warnings
        StatusType status = new StatusType();
        List<ErrorType> warnings = new ArrayList<>();
        ErrorType warning = new ErrorType();
        warning.setHostErrorCode("WARNING456");
        warnings.add(warning);
        status.setWarnings(warnings);


        // Call the getHostCode method
        String result = exceptionHandlerUtil.getHostCode(status);

        // Verify that the result is "WARNING456"
        assertEquals("WARNING456", result);
    }
  /*  @Test
    public void testPrintErrorLog() {
        String errorType = "ERROR";
        Exception exception = new Exception("Sample Exception");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("123");
        errorResponse.setId("456");
        errorResponse.setMessage("Sample Message");
        Error error1 = new Error();
        error1.setErrorCode("");
        errorResponse.addError(error1);

        exceptionHandlerUtil.printErrorLog(errorType, exception, errorResponse);

        System.out.flush();
        String capturedLog = outputStream.toString().trim();

        assertEquals("ERROR Sample Exception Sample Message", capturedLog);
    }*/

    /**
     * Test build error response.
     */
    @Test
     void testBuildErrorResponse() {
        // Mock HttpServletRequest and Exception
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Generated_id")).thenReturn("xyz");
        Exception exception = new Exception("Sample Exception");

        // Mocking the behavior of the dependencies
        when(request.getRequestURI()).thenReturn("/sample-uri");
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/sample-uri"));

        // Test with different exception types
        String errorType = "ERROR";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        // Test with BindException
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", "fieldName", "Sample field error");
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));
        ConstraintViolationException exceptions = mock(ConstraintViolationException.class);
        ConstraintViolation<?> constraintViolation = mock(ConstraintViolation.class);
        when(constraintViolation.getMessage()).thenReturn("Constraint violation message");
        when(exceptions.getConstraintViolations()).thenReturn(new HashSet<>(Collections.singletonList(constraintViolation)));
        ErrorResponse bindErrorResponse = exceptionHandlerUtil.buildErrorResponse(errorType, new BindException(bindingResult), request, status);
        assertNotNull(bindErrorResponse);
        assertEquals(1, bindErrorResponse.getErrors().size());


        // Test with a general Exception
        ErrorResponse generalErrorResponse = exceptionHandlerUtil.buildErrorResponse(errorType, exception, request, status);
        assertNotNull(generalErrorResponse);
        assertEquals(1, generalErrorResponse.getErrors().size());
    }

    }
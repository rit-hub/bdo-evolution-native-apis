package com.bdo.evolution_native.service;

import com.bdo.evolution_native.JsonReader;
import com.bdo.evolution_native.client.CustomerListClient;
import com.bdo.evolution_native.client.model.customerlist.CustListInqRs;
import com.bdo.evolution_native.exception.UseCaseNotValidException;
import com.bdo.evolution_native.model.customerlist.CustomerSearchInquiryRequest;
import com.bdo.evolution_native.model.customerlist.CustomerSearchInquiryResponse;
import com.bdo.evolution_native.service.impl.CustomerAccountSearchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * The type Customer account search service impl test.
 */
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CustomerAccountSearchServiceImplTest {
    @Mock
    private CustomerListClient collateralClient;

    @InjectMocks
    private CustomerAccountSearchServiceImpl customerAccountSearchService;

    private CustListInqRs response;
    private CustomerSearchInquiryRequest request;
    private ServletUriComponentsBuilder servletUriComponentsBuilder;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        request = new CustomerSearchInquiryRequest();
        JsonReader<CustListInqRs> mapper1 = new JsonReader<>(CustListInqRs.class);
        response = (CustListInqRs) mapper1.loadTestJson("CustListInqRs.json");
        servletUriComponentsBuilder = ServletUriComponentsBuilder.fromCurrentRequest();
    }

    /**
     * Test retrive customer.
     */
    @Test
    void testRetriveCustomer() {
        // Create a sample request
        CustomerSearchInquiryRequest request = new CustomerSearchInquiryRequest();
        request.setDebitCardNumber("4404167741811234");

        // Set the necessary fields in sorResponse
        when(collateralClient.customerClient(any())).thenReturn(Mono.just(response));

        // Call the method being tested
        Mono<CustomerSearchInquiryResponse> resultMono = customerAccountSearchService.retriveCustomer(request, servletUriComponentsBuilder);

        // Assert the result
        CustomerSearchInquiryResponse result = resultMono.block();
        assertNotNull(result);
    }

    /**
     * Test validate request and fetch response.
     */
    @Test
    void testValidateRequestAndFetchResponse() {
        // Create a sample request
        request.setDebitCardNumber("4404167741811234");


        // Create a sample response from the collateral client
        when(collateralClient.customerClient(any())).thenReturn(Mono.just(response));

        // Call the method being tested
        Mono<CustomerSearchInquiryResponse> resultMono = customerAccountSearchService.retriveCustomer(request, servletUriComponentsBuilder);

        // Assert the result
        CustomerSearchInquiryResponse result = resultMono.block();
        assertNotNull(result);
        // Add more assertions based on your specific response structure
    }

    /**
     * Test validate request and fetch response with acct id and acct type.
     */
    @Test
    void testValidateRequestAndFetchResponseWithAcctIdAndAcctType() {
        // Create a sample request
        request.setAcctNumber("account123");
        request.setAcctType("savings");

        // Create a sample response from the collateral client
        when(collateralClient.customerClient(any())).thenReturn(Mono.just(response));

        // Call the method being tested
        Mono<CustomerSearchInquiryResponse> resultMono = customerAccountSearchService.retriveCustomer(request, servletUriComponentsBuilder);

        // Assert the result
        CustomerSearchInquiryResponse result = resultMono.block();
        assertNotNull(result);
        // Add more assertions based on your specific response structure
    }

    /**
     * Test validate request and fetch response with cust id.
     */
    @Test
    void testValidateRequestAndFetchResponseWithCustId() {
        // Create a sample request
        request.setCif("cust123");

        // Create a sample response from the collateral client
        when(collateralClient.customerClient(any())).thenReturn(Mono.just(response));

        // Call the method being tested
        Mono<CustomerSearchInquiryResponse> resultMono = customerAccountSearchService.retriveCustomer(request, servletUriComponentsBuilder);

        // Assert the result
        CustomerSearchInquiryResponse result = resultMono.block();
        assertNotNull(result);
        // Add more assertions based on your specific response structure
    }

    /**
     * Test validate request and fetch response with mobile number.
     */
    @Test
    void testValidateRequestAndFetchResponseWithMobileNumber() {
        // Create a sample request
        request.setMobileNumber("1234567890");

        // Create a sample response from the collateral client
        when(collateralClient.customerClient(any())).thenReturn(Mono.just(response));

        // Call the method being tested
        Mono<CustomerSearchInquiryResponse> resultMono = customerAccountSearchService.retriveCustomer(request, servletUriComponentsBuilder);

        // Assert the result
        CustomerSearchInquiryResponse result = resultMono.block();
        assertNotNull(result);
        // Add more assertions based on your specific response structure
    }

    /**
     * Test validate request and fetch response with email address.
     */
    @Test
    void testValidateRequestAndFetchResponseWithEmailAddress() {
        // Create a sample request
        request.setEMailAddress("test@example.com");

        // Create a sample response from the collateral client
        when(collateralClient.customerClient(any())).thenReturn(Mono.just(response));

        // Call the method being tested
        Mono<CustomerSearchInquiryResponse> resultMono = customerAccountSearchService.retriveCustomer(request, servletUriComponentsBuilder);

        // Assert the result
        CustomerSearchInquiryResponse result = resultMono.block();
        assertNotNull(result);
        // Add more assertions based on your specific response structure
    }

    /**
     * Test validate request and fetch response with tax id.
     */
    @Test
    void testValidateRequestAndFetchResponseWithTaxId() {
        // Create a sample request
        request.setTaxId("123456");

        // Create a sample response from the collateral client
        when(collateralClient.customerClient(any())).thenReturn(Mono.just(response));

        // Call the method being tested
        Mono<CustomerSearchInquiryResponse> resultMono = customerAccountSearchService.retriveCustomer(request, servletUriComponentsBuilder);

        // Assert the result
        CustomerSearchInquiryResponse result = resultMono.block();
        assertNotNull(result);
        // Add more assertions based on your specific response structure
    }

    /**
     * Test validate request and fetch response with names and birth date.
     */
    @Test
    void testValidateRequestAndFetchResponseWithNamesAndBirthDate() {
        // Create a sample request
        request.setFirstName("John");
        request.setSecondName("Doe");
        request.setLastName("Smith");
        request.setBirthDt("1990-05-20"); // Replace with a valid date

        // Create a sample response from the collateral client
        when(collateralClient.customerClient(any())).thenReturn(Mono.just(response));

        // Call the method being tested
        Mono<CustomerSearchInquiryResponse> resultMono = customerAccountSearchService.retriveCustomer(request, servletUriComponentsBuilder);

        // Assert the result
        CustomerSearchInquiryResponse result = resultMono.block();
        assertNotNull(result);
        // Add more assertions based on your specific response structure
    }

    /**
     * Test validate request and fetch response invalid case.
     */
    @Test
    void testValidateRequestAndFetchResponseInvalidCase() {
        // Mock the response from the collateral client
        CustListInqRs mockResponse = mock(CustListInqRs.class);
        when(collateralClient.customerClient(any())).thenReturn(Mono.just(mockResponse));

        // Call the method being tested and expect an exception
        // Refactored lambda expression
        assertThrows(UseCaseNotValidException.class, () -> customerAccountSearchService.retriveCustomer(request, servletUriComponentsBuilder));

    }

    /**
     * Test retrive customer with null request.
     */
    @Test
    void testRetriveCustomerWithNullRequest() {
        // Assert that the result is null
        assertThrows(UseCaseNotValidException.class, () -> {
            customerAccountSearchService.retriveCustomer(null, servletUriComponentsBuilder);
        });
    }

    /**
     * Test retrive customer with null response 2.
     */

    /**
     * Test validate request and fetch response with invalid tax id.
     */
    @Test
    void testValidateRequestAndFetchResponseWithInvalidTaxId() {
        request.setTaxId(null);

        when(collateralClient.customerClient(any())).thenReturn(Mono.just(response));

        assertThrows(UseCaseNotValidException.class, () -> {
            customerAccountSearchService.retriveCustomer(request, servletUriComponentsBuilder);
        });
    }

    /**
     * Test validate request and fetch response with invalid birth date.
     */
    @Test
    void testValidateRequestAndFetchResponseWithInvalidBirthDate() {
        request.setFirstName("John");
        request.setSecondName("Doe");
        request.setLastName("Smith");
        request.setBirthDt(null);

        when(collateralClient.customerClient(any())).thenReturn(Mono.just(response));

        assertThrows(UseCaseNotValidException.class, () -> {
            customerAccountSearchService.retriveCustomer(request, servletUriComponentsBuilder);
        });
    }

    /**
     * Test validate request and fetch response with invalid last name.
     */
    @Test
    void testValidateRequestAndFetchResponseWithInvalidLastName() {
        request.setFirstName("John");
        request.setSecondName("joe");
        request.setLastName(null);
        request.setBirthDt("1234");

        when(collateralClient.customerClient(any())).thenReturn(Mono.just(response));

        assertThrows(UseCaseNotValidException.class, () -> {
            customerAccountSearchService.retriveCustomer(request, servletUriComponentsBuilder);
        });
    }

    /**
     * Test validate request and fetch response with no account type.
     */
    @Test
    void testValidateRequestAndFetchResponseWithNoAccountType() {
        request.setAcctNumber("account123");

        when(collateralClient.customerClient(any())).thenReturn(Mono.just(response));

        assertThrows(UseCaseNotValidException.class, () -> {
            customerAccountSearchService.retriveCustomer(request, servletUriComponentsBuilder);
        });
    }

    /**
     * Test validate request and fetch response with invalid mobile number.
     */
    @Test
    void testValidateRequestAndFetchResponseWithInvalidMobileNumber() {
        request.setMobileNumber(null);

        when(collateralClient.customerClient(any())).thenReturn(Mono.just(response));

        assertThrows(UseCaseNotValidException.class, () -> {
            customerAccountSearchService.retriveCustomer(request, servletUriComponentsBuilder);
        });
    }

    /**
     * Test validate request and fetch response with null debit card and null account id.
     */
    @Test
    void testValidateRequestAndFetchResponseWithNullDebitCardAndNullAccountId() {
        request.setDebitCardNumber(null);
        request.setAcctNumber(null);
        request.setAcctType(null);

        assertThrows(UseCaseNotValidException.class, () -> {
            customerAccountSearchService.retriveCustomer(request, servletUriComponentsBuilder);
        });
    }

    /**
     * Test validate request and fetch response with null cust id and null mobile number.
     */
    @Test
    void testValidateRequestAndFetchResponseWithNullCustIdAndNullMobileNumber() {
        request.setCif(null);
        request.setMobileNumber(null);

        assertThrows(UseCaseNotValidException.class, () -> {
            customerAccountSearchService.retriveCustomer(request, servletUriComponentsBuilder);
        });
    }

    /**
     * Test validate request and fetch response with null email address and null tax id.
     */
    @Test
    void testValidateRequestAndFetchResponseWithNullEmailAddressAndNullTaxId() {
        request.setEMailAddress(null);
        request.setTaxId(null);

        assertThrows(UseCaseNotValidException.class, () -> {
            customerAccountSearchService.retriveCustomer(request, servletUriComponentsBuilder);
        });
    }

    /**
     * Test validate request and fetch response with null first and last name.
     */
    @Test
    void testValidateRequestAndFetchResponseWithNullFirstAndLastName() {
        request.setFirstName(null);
        request.setLastName(null);

        assertThrows(UseCaseNotValidException.class, () -> {
            customerAccountSearchService.retriveCustomer(request, servletUriComponentsBuilder);
        });
    }

    /**
     * Test validate request and fetch response with null birth date and null account type.
     */
    @Test
    void testValidateRequestAndFetchResponseWithNullBirthDateAndNullAccountType() {
        request.setBirthDt(null);
        request.setAcctType(null);

        assertThrows(UseCaseNotValidException.class, () -> {
            customerAccountSearchService.retriveCustomer(request, servletUriComponentsBuilder);
        });
    }


    /**
     * Test validate request and fetch response with invalid first last date of birth.
     */
    @Test
    void testValidateRequestAndFetchResponseWithInvalidFirstLastDateOfBirth() {
        request.setFirstName(null);
        request.setSecondName(null);
        request.setLastName(null);
        request.setBirthDt(null);

        when(collateralClient.customerClient(any())).thenReturn(Mono.just(response));

        assertThrows(UseCaseNotValidException.class, () -> {
            customerAccountSearchService.retriveCustomer(request, servletUriComponentsBuilder);
        });
    }

}
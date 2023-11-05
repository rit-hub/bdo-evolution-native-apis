package com.bdo.evolution_native.model.customerlist;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * The type Customer search inquiry request.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@Valid
public class CustomerSearchInquiryRequest {

    @Size(min = 16, max = 16, message = EvolutionConstantUtils.DEBITCARD_SIZE_VALIDATION)
    @Pattern(regexp = "^\\d+$", message = EvolutionConstantUtils.DEBITCARD_NUMBER_VALIDATION)
    @JsonProperty("debitCardNumber")
    private String debitCardNumber;
    @Size(min = 10, max = 10, message = EvolutionConstantUtils.CUSTID_SIZE_VALIDATION)
    @JsonProperty("cif")
    private String cif;
    @Size(max = 12, message = EvolutionConstantUtils.ACCID_SIZE_VALIDATION)
    @JsonProperty("acctNumber")
    private String acctNumber;
    @Size(min = 2, max = 2, message = EvolutionConstantUtils.ACCTTYPE_SIZE_VALIDATION)
    @JsonProperty("acctType")
    private String acctType;
    @Size(min = 11, max = 13, message = EvolutionConstantUtils.MOBILE_NUMBER_SIZE_VALIDATION)
    @JsonProperty("mobileNum")
    private String mobileNumber;
    @Size(min = 14, max = 14, message = EvolutionConstantUtils.TAXID_SIZE_VALIDATION)
    @JsonProperty("taxId")
    private String taxId;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        , message = EvolutionConstantUtils.EMAIL_VALIDATION)
    @Size(max = 50 , message = EvolutionConstantUtils.EMAIL_SIZE_VALIDATION )
    @JsonProperty("eMailAddress")
    private String eMailAddress;
    @Size(max = 30, message = EvolutionConstantUtils.FIRSTNAME_SIZE_VALIDATION)
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("secondName")
    @Size(max = 30, message = EvolutionConstantUtils.SECONDNAME_SIZE_VALIDATION)
    private String secondName = "";
    @Size(max = 30, message = EvolutionConstantUtils.LASTNAME_SIZE_VALIDATION)
    @JsonProperty("lastName")
    private String lastName;
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|1\\d|2\\d|30|31)$"
        , message = EvolutionConstantUtils.BIRTHDATE_VALIDATION)
    @JsonProperty("birthDt")
    private String birthDt;
    @JsonProperty("searchType")
    private String searchType = "00";
    @JsonProperty("customerType")
    private String customerType = "";
}

package com.bdo.evolution_native.constants;

/**
 * This is for Evolution constants
 */
public final class EvolutionConstantUtils {

    public static final String BASE_PACKAGE = "com.bdo.evolution_native";
    public static final String API_TITLE = "Evolution Native Business APIs";
    public static final String VERSION = "1.0";
    public static final String SPACE = " ";

    public static final String COLON = ": ";

    public static final String EMPTY_STRING = "";

    public static final Integer ZERO = 0;

    public static final Integer ONE = 1;

    public static final String EMPTY_SOR_RESPONSE = "Empty/Null Response from SOR";

    public static final String EMPTY_OAUTH_RESPONSE = "Empty/Null Response from Authorization API";
    public static final String INVALID_DATA_FROM_SOR = "Unexpected error occurs during processing SOR response";

    public static final String INVALID_DATA_FROM_OAUTH_SERVER = "Unexpected error occurs during processing "
        + "Authorization Server response";
    public static final String TYPEMISMATCH_ERROR = "TypeMismatch: Failed to convert value of different data types";
    public static final String INVALID_HTTP_MESSAGE = "NotReadable: Illegal Characters in HttpMessage";
    public static final String PATH_NOT_FOUND = "Invalid Request Path";
    public static final String ACCESS_DENIED = "Access Denied";

    public static final String BASIC = "Basic ";
    public static final String UNPROCESSABLE_ENTITY_ERROR = "Unprocessable Entity Error";
    public static final String RETRY_AFTER_HEADER = "Retry-After";
    public static final Integer RETRY_AFTER_VALUE = 5;
    public static final String SOR_CONNECT_ERROR = "Connection Error At SOR";

    public static final String OAUTH_API_CONNECT_ERROR = "Connection Error At Authorization API";
    public static final String ERROR_CODE_NAME = "UK.OBIE";
    public static final String ANY_ERRORS = "Some error occur at server";
    public static final String METHOD_START = "Execution Start for method :: ";
    public static final String METHOD_END = "Execution End for method :: ";
    public static final String METHOD_EXECUTION_TIME = "Elapsed Time (in Milliseconds) :: ";
    public static final String EXCEPTION_MESSAGE_NULL = "Something Went Wrong";
    public static final String VALIDATION_FAILED = "Validation Failed";
    public static final String INTERACTION_ID_HEADER = "x-fapi-interaction-id";
    public static final String CHARSET_UTF8 = ";charset=utf-8";
    public static final String SOR_INVALID_REQUEST = "Invalid Request At SOR";

    public static final String OAUTH_INVALID_REQUEST = "Invalid Request At Authorization API";

    public static final String SOR_NOT_FOUND = "Record Not Found At SOR";

    public static final String OAUTH_NOT_FOUND = "Record Not Found At Authorization API";

    public static final String SOR_ACCESS_DENIED = "Access Denied At SOR";

    public static final String OAUTH_ACCESS_DENIED = "Access Denied At Authorization API";

    public static final String SOR_SERVER_ERROR = "Internal Server Error At SOR";

    public static final String OAUTH_SERVER_ERROR = "Internal Server Error At Authorization API";

    public static final String SOR_UNAUTHORIZED = "Authentication Failed At SOR";

    public static final String OAUTH_UNAUTHORIZED = "Authentication Failed At Authorization API";

    public static final String SOR_TIMEOUT = "Request Timeout At SOR";

    public static final String OAUTH_TIMEOUT = "Request Timeout At Authorization API";

    public static final String SOR_ANY_ERROR = "Error Occurred At SOR";

    public static final String OAUTH_ANY_ERROR = "Error Occurred At Authorization API";

    public static final String LOAN_ID_HEADER_MISSING_VALIDATION = "{LoanId.header.missing}";
    public static final String LOAN_ID_HEADER_SIZE_VALIDATION = "{LoanId.header.invalid.size}";
    public static final String LOAN_ID_INVALID_HEADER_VALIDATION = "{LoanId.invalid.header}";
    public static final String EVOLUTION_FAILED_STATUS = "SOR response with failed status";

    public static final String CACHE_NAME = "token";

    public static final String CACHE_NOT_FOUND = "Cache not found";

    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String BEARER = "Bearer ";
    public static final String CHANNEL_ID_HEADER = "channelId";
    public static final String SOURCE_HEADER = "source";
    public static final String AUTH_DATE = "x-fapi-auth-date: {} \n";

    public static final String CUST_ADDRESS = "x-fapi-customer-ip-address: {} \n";

    public static final String INTERACTION_ID = "x-fapi-interaction-id: {} \n";

    public static final String CUST_USER_AGENT = "x-customer-user-agent: {} \n";
    public static final String GENERATED_ID = "Generated_id: {} \n";

    public static final String HEADERS = "Headers: ";
    public static final String AUTH_DATE_CONST = "x-fapi-auth-date";

    public static final String CUST_ADDRESS_CONST = "x-fapi-customer-ip-address";

    public static final String INTERACTION_ID_CONST = "x-fapi-interaction-id";

    public static final String CUST_USER_AGENT_CONST = "x-customer-user-agent";
    public static final String GENERATED_ID_CONST = "Generated_id";

    /**
     * The constant SWAGGER_CONSTANT.
     */
    public static final String SWAGGER_CONSTANT = "com.bdo.microservice.pom";

    /**
     * The constant SWAGGER_CONSTANT_TITLE.
     */
    public static final String SWAGGER_CONSTANT_TITLE = "Customer List Business APIs";
    /**
     * The constant SWAGGER_CONSTANT_DESCRIPTION.
     */
    public static final String SWAGGER_CONSTANT_DESCRIPTION = "Customer List Business APIs";
    /**
     * The constant SWAGGER_CONSTANT_VERSION.
     */
    public static final String SWAGGER_CONSTANT_VERSION = "1";
    /**
     * The constant USECASE_NOT_VALID.
     */
    public static final String USECASE_NOT_VALID = "provided usecase is not valid";

    /**
     * The constant NUMBER_FIVE.
     */
    public static final int NUMBER_FIVE = 5;
    /**
     * The constant CUSTID_SIZE_VALIDATION.
     */
    public static final String CUSTID_SIZE_VALIDATION = "{custID.size.message}";
    /**
     * The constant ACCTTYPE_SIZE_VALIDATION.
     */
    public static final String ACCTTYPE_SIZE_VALIDATION = "{acctType.size.message}";
    /**
     * The constant ACCID_SIZE_VALIDATION.
     */
    public static final String ACCID_SIZE_VALIDATION = "{accId.size.message}";
    /**
     * The constant MOBILE_NUMBER_SIZE_VALIDATION.
     */
    public static final String MOBILE_NUMBER_SIZE_VALIDATION = "{mobileNumber.size.message}";
    /**
     * The constant TAXID_SIZE_VALIDATION.
     */
    public static final String TAXID_SIZE_VALIDATION = "{taxID.size.message}";
    /**
     * The constant EMAIL_VALIDATION.
     */
    public static final String EMAIL_VALIDATION = "{email.validation.message}";
    /**
     * The constant FIRSTNAME_SIZE_VALIDATION.
     */
    public static final String FIRSTNAME_SIZE_VALIDATION = "{firstName.size.message}";
    /**
     * The constant LASTNAME_SIZE_VALIDATION.
     */
    public static final String LASTNAME_SIZE_VALIDATION = "{lastName.size.message}";
    /**
     * The constant BIRTHDATE_VALIDATION.
     */
    public static final String BIRTHDATE_VALIDATION = "{birthdate.pattern.message}";
    /**
     * The constant SIX.
     */
    public static final int SIX = 6;
    /**
     * The constant ONE_STRING.
     */
    public static final String ONE_STRING = "1";
    /**
     * The constant DEBITCARD_SIZE_VALIDATION.
     */
    public static final String DEBITCARD_SIZE_VALIDATION = "{debitCard.size.validation}";
    /**
     * The constant THREE_ZERO.
     */
    public static final String THREE_ZERO = "000";
    public static final String HEADER_MISSING = "Header Should not be blank";
    public static final String SECONDNAME_SIZE_VALIDATION = "{second.size.validation}";
    public static final String EMAIL_SIZE_VALIDATION = "{email.size.validation}";
    public static final String DEBITCARD_NUMBER_VALIDATION = "{debitcard.number.validation}";
    public static final String PARSED_FLAG_SIZE_MESSAGE = "{parsedflag.size.validation}";
    public static final String MEMO_FLAG_SIZE_MESSAGE = "{memoflag.size.validation}";
    public static final String REQUEST_MEMO_FLAG_SIZE_MESSAGE = "{requestmemoflag.size.validation}";
    public static final String TICKLER_FLAG_SIZE_MESSAGE = "{ticklerflag.size.validation}";
    public static final String REQUEST_TICKLER_FLAG_SIZE_MESSAGE = "{requestticklerflag.size.validation}";
    public static final String TELEX_ANSWER_BACK_NUMBER_SIZE_MESSAGE = "{telexanswerback.size.validation}";
    public static final String SOURCE_CODE_SIZE_MESSAGE = "{sourcecode.size.validation}";
    public static final String CONTACT_PREFERENCE_TEXT_SIZE_MESSAGE = "{contactpreferencetext.size.validation}";
    public static final String CUSTOMER_LINKAGE_SIZE_MESSAGE = "{customerlinkage.size.validation}";
    public static final String LOYALTY_CARD_NUMBER_SIZE_MESSAGE = "{loyaltycard.size.validation}";
    public static final String ACCOUNT_ID_SIZE_MESSAGE = "{accountId.size.validation}";
    public static final String ACCOUNT_TYPE_FORMAT_MESSAGE = "{account.type.format.validation}";
    public static final String CARD_HOLDER_FLAG_SIZE_MESSAGE = "{cardholderflag.size.validation}";
    public static final String LAST_NAME_SIZE_MESSAGE = "{lastname.size.validation}";
    public static final String FIRST_NAME_SIZE_MESSAGE = "{firstname.size.validation}";
    public static final String MIDDLE_NAME_SIZE_MESSAGE = "{middlename.size.validation}";
    public static final String NAME_SUFFIX_SIZE_MESSAGE = "{namesuffix.size.validation}";
    public static final String BUSINESS_NAME_SIZE_MESSAGE = "{bussinessname.size.validation}";
    public static final String REGEX_FOR_DATE = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|1\\d|2\\d|30|31)$";
    public static final String LINE1_SIZE_MESSAGE = "{line1.size.validation}";
    public static final String LINE2_SIZE_MESSAGE = "{line2.size.validation}";
    public static final String LINE3_SIZE_MESSAGE = "{line3.size.validation}";
    public static final String LINE4_SIZE_MESSAGE = "{line4.size.validation}";
    public static final String REGION_SIZE_MESSAGE = "{region.size.validation}";
    public static final String PROVINCE_SIZE_MESSAGE = "{province.size.validation}";
    public static final String PASSPORT_ID_SIZE_MESSAGE = "{passportid.size.validation}";
    public static final String SALUTATION_SIZE_MESSAGE = "{salutation.size.validation}";
    public static final String DATE_FORMAT_MESSAGE = "{date.format.validation}";
    public static final String CUSTOMER_DOCUMENT_CODE_SIZE_MESSAGE = "{customerdocumentcode.size.validation}";
    public static final String CUSTOEMR_DEALER_GROUP_FLAG_SIZE_MESSAGE = "{customerdealergroup.size.validation}";
    public static final String CUSTOMER_COLLECTION_STATUS_SIZE_MESSAGE = "{customercollectionstatus.size.validation}";
    public static final String CONTACT_NAME_SIZE_MESSAGE = "{contactname.size.validation}";
    public static final String CUSTOMER_NUMBER_SIZE_MESSAGE = "{customernumber.size.validation}";
    public static final String SHORT_NAME_SIZE_MESSAGE = "{shortname.size.validation}";
    public static final String DERIVED_SHORT_NAME_SIZE_MESSAGE = "{derivedshortname.size.validation}";
    public static final String INQUIRY_LEVEL_CODE_SIZE_MESSAGE = "{inquirylevelcode.size.validation}";
    public static final String MAINTENANCE_LEVEL_CODE_SIZE_MESSAGE = "{maintenancelevelcode.size.validation}";
    public static final String OWN_HOME_CODE_SIZE_MESSAGE = "{ownhomecode.size.validation}";
    public static final String BUISNESS_EMAIL_SIZE_MESSAGE = "{bussinessemail.size.validation}";
    public static final String NATIONAL_ID_SIZE_MESSAGE = "{nationalId.size.validation}";
    public static final String PHONE_TYPE_SIZE_MESSAGE = "{phonetype.size.validation}";
    public static final String PHONE_TYPE_FORMAT_MESSAGE = "{phonetype.format.message}";
    public static final String CUSTOMER_NUMBER_SIZE_VALIDATION = "{customerNumber.size.message}";
    public static final String DATE_VALIDATION = "{date.pattern.message}";
    public static final String JOB_TITLE_TYPE_SIZE_VALIDATION = "{jobTitleType.size.validation}";
    public static final String PAYROLLID_SIZE_VALIDATION = "{payrollId.size.validation}";
    public static final String INVALID_REQUEST_BODY = "Invalid Request Body";
    public static final String BRANCHID_SIZE = "{branchId.size.validation}";
    public static final String TAXID_INITIATE_SIZE = "{taxId.size.initiate.validation}";
    public static final String VATEXEMPTIONCODE_SIZE_VALIDATION = "{vatexemptioncode_size_validation}";
    public static final String RELATION_CODE_SIZE_MESSAGE = "{RelationCode.size.validation}";
    public static final String ALIAS_ACCOUNT_ID_SIZE_MESSAGE = "{AliasBankAccountId.size.validation}";
    public static final String ALIAS_ACCOUNT_ID_MASKED_SIZE_MESSAGE = "{AliasAcctIdMasked.size.validation}";
    public static final String ACCOUNT_TYPE_SIZE_MESSAGE = "{AccountType.size.validation}";
    public static final String BANK_ACCOUNT_ID_SIZE_MESSAGE = "{AccountId.size.validation}";
    public static final String BANK_ACCOUNT_ID_MASKED_SIZE_MESSAGE = "{AccountIdMasked.size.validation}";
    public static final String TAX_LIABILITY_PERCENTAGE_AMOUNT_SIZE_VALIDATION = "{TaxLiabilityPctAmt.size.validation}";
    public static final String NOTICE_FLAG_DESC_SIZE_VALIDATION = "{NoticeFlagDescription.size.validation}";
    public static final String RELATION_CODE_NOT_BLANK = "{RelationCode.notBlank.validation}";
    public static final String ACCOUNT_TYPE_NOT_BLANK = "{AccountType.notBlank.validation}";
    public static final String ACCOUNT_ID_NOT_BLANK = "{AccountId.notBlank.validation}";
    public static final String CUSTOMER_NUMBER_NOT_BLANK = "{CustomerNumber.notBlank.validation}";
    public static final String ISOID_MANDATORY_VALIDATION = "{IsoId.mandatory.validation}";
    public static final String CARDGENERATENUMBER_MANDATORY_VALIDATION = "{CardGenerateNumber.mandatory.validation}";
    public static final String CARDTYPECODE_MANDATORY_VALIDATION = "{CardTypeCode.mandatory.validation}";
    public static final String CUSTOMER_NUMBER_MASKED_SIZE_VALIDATION = "{CustomerNumberMasked.size.validation}";
    public static final String LANGUAGE_CODE_SIZE_VALIDATION = "{LanguageCode.size.validation}";
    public static final String ISOID_NUMBER_VALIDATION = "{IsoId.number.validation}";
    public static final String VIP_CLASS_SIZE_VALIDATION = "{VipClass.size.validation}";
    public static final String CARD_GENERATED_NUMBER_SIZE_VALIDATION = "{CardGeneratedNumber.size.validation}";
    public static final String EMOSSING_NAME_SIZE_VALIDATION = "{EmossingName.size.validation}";
    public static final String CARD_TYPE_CODE_SIZE_VALIDATION = "{CardTypeCode.size.validation}";
    public static final String CARD_STATUS_CODE_SIZE_VALIDATION = "{CardStatusCode.size.validation}";
    public static final String EXPIRATION_SPECIFIC_DAY_SIZE_VALIDATION = "{ExpirationSpecificDay.size.validation}";
    public static final String BANK_ACCOUNT_ID_CARD_SIZE_MESSAGE = "{BankAccountId.size.validation.card}";
    public static final String SEQUENCEID_SIZE_VALIDATION = "{SequenceId.size.validation}";
    public static final String ACCOUNT_TYPE_FORMAT_MESSAGE_ADDCARD = "{AccountType.format.message.addcard}";

    private EvolutionConstantUtils() {

    }
}


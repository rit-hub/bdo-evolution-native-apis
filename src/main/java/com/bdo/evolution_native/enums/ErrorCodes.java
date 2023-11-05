package com.bdo.evolution_native.enums;

/**
 * This is an enum for different error codes.
 */
public enum ErrorCodes {
    EXPECTED("UK.OBIE.Field.Expected"),
    INVALID("UK.OBIE.Field.Invalid"),
    INVALIDDATE("UK.OBIE.Field.InvalidDate"),
    MISSING("UK.OBIE.Field.Missing"),
    UNEXPECTED("UK.OBIE.Field.Unexpected"),
    INVALID_HEADER("UK.OBIE.Header.Invalid"),
    MISSING_HEADER("UK.OBIE.Header.Missing"),
    UNEXPECTEDERROR("UK.OBIE.UnexpectedError"),

    INTERNAL_SERVER_ERROR("UK.OBIE.InternalServerError"),
    BAD_REQUEST("UK.OBIE.BadRequest"),
    NOT_FOUND("UK.OBIE.NotFound"),
    FORBIDDEN("UK.OBIE.Forbidden"),
    UNPROCESSABLE_ENTITY("UK.OBIE.UnprocessableEntity"),
    BAD_GATEWAY("UK.OBIE.BadGateway"),
    UNAUTHORIZED("UK.OBIE.UnAuthorized"),
    REQUESTTIMEOUT("UK.OBIE.RequestTimeout");

    private final String code;

    ErrorCodes(final String code) {
        this.code = code;
    }

    public String toCode() {
        return this.code;
    }
}

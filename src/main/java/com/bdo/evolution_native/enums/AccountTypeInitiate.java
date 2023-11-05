package com.bdo.evolution_native.enums;

public enum AccountTypeInitiate {
    DINERSCLUB("DINERSCLUB"),
    VISA("VISA"),
    MASTERCARD("MASTERCARD"),
    ATM("ATM");
    private final String code;

    AccountTypeInitiate(final String code) {
        this.code = code;
    }

    public String toCode() {
        return this.code;
    }
}

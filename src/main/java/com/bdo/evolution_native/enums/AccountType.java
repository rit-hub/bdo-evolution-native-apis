package com.bdo.evolution_native.enums;

/**
 * This is for AccountType enum
 */
public enum AccountType {

    DD("DD"),
    SV("SV"),
    TM("TM"),
    LN("LN");

    private final String code;

    AccountType(final String code) {
        this.code = code;
    }

    public String toCode() {
        return this.code;
    }
}

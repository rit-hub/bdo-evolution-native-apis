package com.bdo.evolution_native.enums;

public enum PhoneType {
    HOME("HOME"),
    CELLULAR("CELLULAR"),
    FAX("FAX"),
    BUSINESS("BUSINESS");

    private final String code;

    PhoneType(final String code) {
        this.code = code;
    }

    public String toCode() {
        return this.code;
    }
}

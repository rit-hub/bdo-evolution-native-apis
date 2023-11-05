package com.bdo.evolution_native.enums;

/**
 * This is an enum for Domain name.
 */
public enum Domain {
    EVOLUTION("EVOLUTION");

    private final String code;

    Domain(final String code) {
        this.code = code;
    }

    public String toCode() {
        return this.code;
    }
}

package com.zubair.ecommerce.enums;

public enum RoleType {
    CUSTOMER("ROLE_CUSTOMER"),
    SELLER("ROLE_SELLER"),
    ADMIN("ROLE_ADMIN");

    private final String value;

    RoleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

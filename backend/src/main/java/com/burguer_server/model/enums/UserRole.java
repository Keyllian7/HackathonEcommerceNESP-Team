package com.burguer_server.model.enums;

public enum UserRole {
    BUYER("buyer"),
    SELLER("seller"),
    BUYER_INACTIVE("buyer inactive"),
    SELLER_INACTIVE("seller inactive");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

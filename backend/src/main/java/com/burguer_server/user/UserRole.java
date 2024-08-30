package com.burguer_server.user;

public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    BUYER("buyes"),
    SELLER("seller"),
    BUYER_INACTIVE("buyes inactive"),
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

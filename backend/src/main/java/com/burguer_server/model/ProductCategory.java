package com.burguer_server.model;

public enum ProductCategory {
    HAMBURGUER("hamburguer"),
    DRINK("drink");

    private String type;

    ProductCategory(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

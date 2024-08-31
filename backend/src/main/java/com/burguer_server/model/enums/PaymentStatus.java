package com.burguer_server.model.enums;

public enum PaymentStatus {

    PAID("paid"),
    PENDING("pending"),
    DENIED("denied");

    private String type;

    PaymentStatus(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

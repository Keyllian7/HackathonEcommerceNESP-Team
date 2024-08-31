package com.burguer_server.model.enums;

public enum PaymentMethod {

    CARD("card"),
    CASH("cash"),
    PIX("pix");

    private String type;

    PaymentMethod(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

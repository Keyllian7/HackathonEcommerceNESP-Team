package com.burguer_server.payloads;

import com.burguer_server.model.Seller;
import com.burguer_server.model.Stock;
import com.burguer_server.model.enums.UserRole;

public record SellerPayloadRequest(String email,
                                   String password,
                                   UserRole role,
                                   Stock sellerStock) {

    public SellerPayloadRequest(Seller seller) {
        this(seller.getEmail(), seller.getPassword(), seller.getRole(), seller.getSellerStock());
    }
}

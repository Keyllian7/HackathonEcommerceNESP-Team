package com.burguer_server.payloads.seller;

import com.burguer_server.model.user.Seller;
import com.burguer_server.model.product.Stock;
import com.burguer_server.model.enums.UserRole;

public record SellerPayloadResponse(Long id,
                                    String email,
                                    String password,
                                    UserRole role,
                                    Stock sellerStock) {

    public SellerPayloadResponse(Seller seller) {
        this(seller.getId(), seller.getEmail(), seller.getPassword(), seller.getRole(), seller.getSellerStock());
    }


}

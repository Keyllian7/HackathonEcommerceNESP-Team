package com.burguer_server.payloads.seller;

import com.burguer_server.model.user.Seller;
import com.burguer_server.model.product.Stock;
import com.burguer_server.model.enums.UserRole;
import jakarta.validation.constraints.NotNull;

public record SellerPayloadRequest(@NotNull(message = "Não pode deixar o email null") String email,
                                   @NotNull(message = "Não pode deixar a senha null") String password,
                                   UserRole role,
                                   Stock sellerStock) {

    public SellerPayloadRequest(Seller seller) {
        this(seller.getEmail(), seller.getPassword(), seller.getRole(), seller.getSellerStock());
    }
}

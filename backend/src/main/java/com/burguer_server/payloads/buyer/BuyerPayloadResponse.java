package com.burguer_server.payloads.buyer;

import com.burguer_server.model.enums.UserRole;
import com.burguer_server.model.order.Order;
import com.burguer_server.model.user.Adress;
import com.burguer_server.model.user.Buyer;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BuyerPayloadResponse(Long id,
                                   @NotNull(message = "Não pode deixar o email null") String email,
                                   @NotNull(message = "Não pode deixar a senha null") String password,
                                   UserRole role,
                                   String buyerFoneNumber,
                                   Adress buyerAdress,
                                   List<Order> buyerOrdersHistory) {

    public BuyerPayloadResponse(Buyer buyer) {
        this(buyer.getId(), buyer.getEmail(), buyer.getPassword(), buyer.getRole(), buyer.getBuyerFoneNumber(), buyer.getBuyerAdress(), buyer.getBuyerOrdersHistory());
    }
}

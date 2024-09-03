package com.burguer_server.payloads.buyer;

import com.burguer_server.model.enums.UserRole;
import com.burguer_server.model.order.Order;
import com.burguer_server.model.product.Stock;
import com.burguer_server.model.user.Adress;
import com.burguer_server.model.user.Buyer;
import com.burguer_server.model.user.Seller;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BuyerPayloadRequest(@NotNull(message = "Não pode deixar o email null") String email,
                                  @NotNull(message = "Não pode deixar a senha null") String password,
                                  UserRole role,
                                  String buyerFoneNumber,
                                  Adress buyerAdress,
                                  List<Order> buyerOrdersHistory) {

    public BuyerPayloadRequest(Buyer buyer) {
        this(buyer.getEmail(), buyer.getPassword(), buyer.getRole(), buyer.getBuyerFoneNumber(), buyer.getBuyerAdress(), buyer.getBuyerOrdersHistory());
    }
}

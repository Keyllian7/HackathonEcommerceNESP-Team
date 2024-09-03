package com.burguer_server.model.user;

import com.burguer_server.model.enums.UserRole;
import com.burguer_server.model.order.Order;
import com.burguer_server.payloads.buyer.BuyerPayloadRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "buyer")
@EqualsAndHashCode(callSuper = true)
public class Buyer extends User {

    private String buyerFoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "adress_id", referencedColumnName = "adressId")
    private Adress buyerAdress;

    @JsonIgnore
    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    private List<Order> buyerOrdersHistory;

    //private Cart buyerCart;

    public Buyer(Long id, String email, String password, Adress buyerAdress, String buyerFoneNumber, List<Order> buyerOrdersHistory) {
        super(id, email, password, UserRole.BUYER);
        this.buyerAdress = buyerAdress;
        this.buyerFoneNumber = buyerFoneNumber;
        this.buyerOrdersHistory = buyerOrdersHistory;
    }

    public Buyer(){
        this.setRole(UserRole.BUYER);
    }

    public Buyer(BuyerPayloadRequest payload) {
        this.setEmail(payload.email());
        this.setPassword(payload.password());

        if (payload.role() == null || payload.role() != UserRole.BUYER || payload.role() == UserRole.BUYER) {
            this.setRole(UserRole.BUYER);
        }

        this.buyerFoneNumber = payload.buyerFoneNumber();
        this.buyerAdress = payload.buyerAdress();

        if (payload.buyerOrdersHistory() == null) {
            this.buyerOrdersHistory = null;
        }
        this.buyerOrdersHistory = payload.buyerOrdersHistory();

    }


}

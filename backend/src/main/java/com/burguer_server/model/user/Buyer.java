package com.burguer_server.model.user;

import com.burguer_server.model.enums.UserRole;
import com.burguer_server.model.order.Order;
import com.burguer_server.payloads.buyer.BuyerPayloadRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "buyers_tb")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Buyer extends User {

    @Column(nullable = false)
    private String buyerFoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "adress_id", referencedColumnName = "adressId")
    private Adress buyerAdress;

    @JsonIgnore
    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    private List<Order> buyerOrdersHistory;

    public Buyer(Long id, String email, String password, UserRole BUYER_ROLE, Adress buyerAdress, String buyerFoneNumber, List<Order> buyerOrdersHistory) {
        super(id, email, password, BUYER_ROLE);
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

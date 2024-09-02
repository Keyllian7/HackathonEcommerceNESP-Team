package com.burguer_server.model;

import com.burguer_server.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "buyer")
public class Buyer extends User {

    private String buyerFoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "adress_id", referencedColumnName = "adressId")
    private Adress buyerAdress;

    @OneToMany(mappedBy = "buyer")
    private List<Order> buyerOrdersHistory;

    //private Cart buyerCart;



    public Buyer(Long id, String email, String password, UserRole BUYER_ROLE, Adress buyerAdress, String buyerFoneNumber, List<Order> buyerOrdersHistory) {
        super(id, email, password, BUYER_ROLE);
        this.buyerAdress = buyerAdress;
        this.buyerFoneNumber = buyerFoneNumber;
        this.buyerOrdersHistory = buyerOrdersHistory;
    }

    public Buyer(){
        this.setRole(UserRole.BUYER);
    }
}

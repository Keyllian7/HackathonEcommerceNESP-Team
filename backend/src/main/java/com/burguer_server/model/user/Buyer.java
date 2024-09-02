package com.burguer_server.model.user;

import com.burguer_server.model.enums.UserRole;
import com.burguer_server.model.order.Order;
import jakarta.persistence.*;
import lombok.*;

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

    @OneToMany(mappedBy = "buyer")
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
}

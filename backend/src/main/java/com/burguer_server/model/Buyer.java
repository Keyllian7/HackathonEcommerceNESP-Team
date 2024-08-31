package com.burguer_server.model;

import com.burguer_server.user.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "idBuyer")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "buyer")
public class Buyer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBuyer;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "adress_id", referencedColumnName = "adressId")
    private Adress buyerAdress;

    private String buyerFoneNumber;

    @OneToMany(mappedBy = "buyer")
    private List<Order> buyerOrdersHistory = new ArrayList<>();

    //private Cart buyerCart;
}

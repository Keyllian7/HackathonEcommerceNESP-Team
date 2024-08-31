package com.burguer_server.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Buyer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBuyer;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "adress_id", referencedColumnName = "adressId")
    private Adress buyerAdress;

    private String buyerFoneNumber;

    @OneToMany(mappedBy = "buyer")
    private List<Order> buyerOrdersHistory = new ArrayList<>();

    private Cart buyerCart;
}

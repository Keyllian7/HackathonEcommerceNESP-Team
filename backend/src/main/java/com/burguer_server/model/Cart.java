package com.burguer_server.model;

import com.burguer_server.model.user.Buyer;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table (name = "cart_tb")
@EqualsAndHashCode(of = "cartId")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

public class Cart {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long cartId;

        @OneToMany(mappedBy = "cart",fetch = FetchType.EAGER)
        private List<CartItem> cartItens;
        @Column(nullable = false)
        private float deliveryTax;
        @Column(nullable = false)
        private float total;
        @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
        @JoinColumn(name = "buyer_id", referencedColumnName = "buyerId")
        private Buyer buyer;

    }

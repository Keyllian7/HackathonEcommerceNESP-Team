package com.burguer_server.model;

import com.burguer_server.model.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "cart_item_id")
@EqualsAndHashCode(of = "cartItemId")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

public class CartItem {
    @Id
    @GeneratedValue
    private Long cartItemId;
    @ManyToMany
    private Product cartItemProduct;
    @Column(nullable = false)
    private int cartItemQuantity;
    @Column(nullable = false)
    private float cartItemTotal;
    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;
}
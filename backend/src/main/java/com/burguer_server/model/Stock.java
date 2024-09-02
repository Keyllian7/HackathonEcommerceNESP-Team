package com.burguer_server.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "stocks_tb")
@Getter
@Setter
@EqualsAndHashCode(of = "stockId")
@NoArgsConstructor
@AllArgsConstructor
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;


    @ElementCollection
    @CollectionTable(name = "stock_products_tb", joinColumns = @JoinColumn(name = "stock_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity", nullable = false)
    private Map<Product, Integer> stockProduct = new HashMap<>();

    public void updateStockProductQuantityAvailable(Product product, int quantity) {
    }

    public boolean isAvailable(Product product) {
        return false;
    }
}

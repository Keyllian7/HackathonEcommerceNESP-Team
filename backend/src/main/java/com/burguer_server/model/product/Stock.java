package com.burguer_server.model.product;

import com.burguer_server.payloads.seller.SellerPayloadRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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


    /*@ElementCollection
    @CollectionTable(name = "stock_products_tb", joinColumns = @JoinColumn(name = "stock_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity", nullable = false)
    private Map<Product, Integer> stockProduct = new HashMap<>();*/

    @JsonIgnore
    @OneToMany(mappedBy = "stock",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Product> stockProduct = new HashSet<>();


    public void updateStockProductQuantityAvailable(Product product, int quantity) {
    }

    public boolean isAvailable(Product product) {
        return false;
    }

}

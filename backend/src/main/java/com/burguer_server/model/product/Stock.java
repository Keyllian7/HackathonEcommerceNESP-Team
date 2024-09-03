package com.burguer_server.model.product;

import com.burguer_server.payloads.seller.SellerPayloadRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stock")
@Getter
@Setter
@EqualsAndHashCode(of = "stockId")
@NoArgsConstructor
@AllArgsConstructor
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    @JsonIgnore
    @OneToMany(mappedBy = "stock", fetch = FetchType.EAGER)
    private Set<Product> stockProduct = new HashSet<>();

    public void updateStockProductQuantityAvailable(Product product, int quantity) {
    }

    public boolean isAvailable(Product product) {
        return false;
    }

}

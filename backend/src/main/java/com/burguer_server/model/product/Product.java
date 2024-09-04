package com.burguer_server.model.product;

import com.burguer_server.model.enums.ProductCategory;
import com.burguer_server.model.order.OrderItem;
import com.burguer_server.payloads.products.ProductsPayloadResponse;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products_tb")
@Getter
@Setter
@EqualsAndHashCode(of = "productId")
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private float productPrice;

    @Column(nullable = false)
    private String productImageLink;

    @Column(nullable = false, columnDefinition = "text")
    private String productDescription;

    @ManyToOne
    @JoinColumn(name = "stockId")
    private Stock stock;

    public Product(ProductsPayloadResponse payload) {
        this.productCategory = payload.productCategory();
        this.orderItems = payload.orderItems();
        this.productName = payload.productName();
        this.productPrice = payload.productPrice();
        this.productImageLink = payload.productImageLink();
        this.productDescription = payload.productDescription();
        this.stock = payload.stock();
    }
}

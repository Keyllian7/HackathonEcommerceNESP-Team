package com.burguer_server.model.product;

import com.burguer_server.model.enums.ProductCategory;
import com.burguer_server.model.order.OrderItem;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
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

    private String productImageLink;

    @Column(columnDefinition = "text")
    private String productDescription;

    @ManyToOne
    @JoinColumn(name = "stockId")
    private Stock stock;
}

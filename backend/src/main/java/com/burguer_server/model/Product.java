package com.burguer_server.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

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

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private float productPrice;

    private String productImageLink;
    private String productDescription;
}

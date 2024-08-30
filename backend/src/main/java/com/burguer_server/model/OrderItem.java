package com.burguer_server.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orderItem")
@Getter
@Setter
@EqualsAndHashCode(of = "orderItemId")
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    private Product product;
    private int quantity;
    private String notes;

    private Float calculateOrderItemTotal() {
        return 0.0f;
    }
}

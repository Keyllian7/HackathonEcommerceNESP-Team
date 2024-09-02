package com.burguer_server.model.order;

import com.burguer_server.model.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items_tb")
@Getter
@Setter
@EqualsAndHashCode(of = "orderItemId")
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false, columnDefinition = "text")
    private String notes;

    private Float calculateOrderItemTotal() {
        return 0.0f;
    }
}

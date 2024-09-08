package com.burguer_server.model.order;

import com.burguer_server.model.product.Product;
import com.burguer_server.payloads.orderitem.OrderItemPayloadResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @JsonIgnore
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

    public OrderItem(OrderItemPayloadResponse payload){
        this.orderItemId = payload.orderItemId();
        this.product = payload.product();
        this.quantity = payload.quantity();
        this.notes = payload.notes();
    }
}

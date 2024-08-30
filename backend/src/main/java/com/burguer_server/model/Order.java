package com.burguer_server.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order")
@Getter
@Setter
@EqualsAndHashCode(of = "orderId")
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "idBuyer")
    private Buyer buyer;
    private List<OrderItem> orderItens = new ArrayList<>();
    private float orderDeliveryTax;
    private float orderTotal;
    private Payment orderPayment;
    private String currentOrderStatus;

    @OneToMany(mappedBy = "order")
    private List<OrderStatus> statusHistory;

    private float calculateOrderTotal(){
        return 0.0f;
    }
    private void addOrderItem(OrderItem item){

    }
    private void updateOrderItem(OrderItem item){

    }
    private void removeOrderItem(OrderItem item){

    }

}

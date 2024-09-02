package com.burguer_server.model.order;

import com.burguer_server.model.user.Buyer;
import com.burguer_server.model.payment.Payment;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
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

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItens = new ArrayList<>();

    private float orderDeliveryTax;
    private float orderTotal;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id", referencedColumnName = "idPayment")
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

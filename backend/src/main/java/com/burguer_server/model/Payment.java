package com.burguer_server.model;

import com.burguer_server.model.enums.PaymentMethod;
import com.burguer_server.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment")
@Getter
@Setter
@EqualsAndHashCode(of = "idPayment")
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(nullable = false)
    private float total;

    @Column(nullable = false)
    private boolean isPaid;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private void processPayment(){

    }
    private void updatePaymentStatus(/*PaymentStatus paymentStatus*/){

    }
}

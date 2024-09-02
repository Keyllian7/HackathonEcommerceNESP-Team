package com.burguer_server.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_statuses_tb")
@Getter
@Setter
@EqualsAndHashCode(of = "orderStatusId")
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderStatusId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(nullable = false)
    private LocalDateTime orderStatusTimeStamp;

    @Column(nullable = false)
    private String orderStatusDescription;
}

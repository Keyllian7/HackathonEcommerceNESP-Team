package com.burguer_server.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orderItem")
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
    @JoinColumn(name = "idOrder")
    private Order order;

    private LocalDateTime orderStatusTimeStamp;
    private String orderStatusDescription;
}

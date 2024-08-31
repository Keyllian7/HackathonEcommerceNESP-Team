package com.burguer_server.model;

import com.burguer_server.user.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "seller")
@Getter
@Setter
@EqualsAndHashCode(of = "sellerId")
@NoArgsConstructor
@AllArgsConstructor
public class Seller implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Stock sellerStock;

    public void manageStock() {
        // Implementação do método
    }

    public void manageOrders() {
        // Implementação do método
    }
}

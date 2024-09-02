package com.burguer_server.model;

import com.burguer_server.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "seller")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class Seller extends User {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id", referencedColumnName = "stockId")
    private Stock sellerStock;

    public void manageStock() {
        // Implementação do método
    }

    public void manageOrders() {
        // Implementação do método
    }

    public Seller(Long id, String email, String password, Stock sellerStock) {
        super(id, email, password, UserRole.SELLER);
        this.sellerStock = sellerStock;
    }

    public Seller(){
        this.setRole(UserRole.SELLER);
    }
}

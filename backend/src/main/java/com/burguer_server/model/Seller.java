package com.burguer_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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


}

package com.burguer_server.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "adresses_tb")
@Getter
@Setter
@EqualsAndHashCode(of = "adressId")
@NoArgsConstructor
@AllArgsConstructor
public class Adress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adressId;

    @Column(length = 20)
    private String adressCep;

    @Column(nullable = false)
    private String adressStreet;

    @Column(nullable = false)
    private String adressCity;

    @Column(nullable = false)
    private int adressNumber;

    @Column(nullable = false)
    private String adressComplement;

    @Column(nullable = false)
    private String adressReference;
}

package com.burguer_server.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "adress")
@Getter
@Setter
@EqualsAndHashCode(of = "adressId")
@NoArgsConstructor
@AllArgsConstructor
public class Adress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adressId;
    private String adressCep;

    @Column(nullable = false)
    private String adressStreet;

    @Column(nullable = false)
    private String adressCity;

    private int adressNumber;
    private String adressComplement;
    private String adressReference;
}

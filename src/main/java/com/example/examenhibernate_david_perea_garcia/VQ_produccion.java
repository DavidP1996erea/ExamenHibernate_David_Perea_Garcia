package com.example.examenhibernate_david_perea_garcia;

import jakarta.persistence.*;

@Entity
@Table(name = "VQ_Naves")

public class VQ_produccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name ="ganadero")
    private String ganadero;

    @Column(name="ubicacion")
    private String ubicacion;



}

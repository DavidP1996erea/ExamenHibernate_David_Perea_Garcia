package com.example.examenhibernate_david_perea_garcia;


import jakarta.persistence.*;

@Entity
@Table(name = "VQ_Naves")
public class VQ_Naves {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name ="ganadero")
    private String ganadero;

    @Column(name="ubicacion")
    private String ubicacion;


    public VQ_Naves() {

    }


    public VQ_Naves(String ganadero, String ubicacion) {
        this.ganadero = ganadero;
        this.ubicacion = ubicacion;
    }


    public int getId() {
        return id;
    }

    public String getGanadero() {
        return ganadero;
    }

    public void setGanadero(String ganadero) {
        this.ganadero = ganadero;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}

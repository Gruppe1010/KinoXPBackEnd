package com.example.gruppe10.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="theaters")
public class Theater {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name="id_theater")
    private int id;

    @NotNull
    private int rows;

    @NotNull
    private int seatsPrRow;







}

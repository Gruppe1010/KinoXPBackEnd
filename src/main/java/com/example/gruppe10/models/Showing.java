package com.example.gruppe10.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Showing {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name="id_showing")
    private int id;
    //nullable = false fordi et showing har altid en movie
    @ManyToOne
    @JoinColumn(name="id_movie", nullable = false)
    private Movie movie;
    
    public Showing() {
    }
}

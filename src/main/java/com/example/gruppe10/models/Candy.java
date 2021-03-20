package com.example.gruppe10.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Candy implements Comparable<Candy>{

    // Attributter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name="id_candy")
    private int id;
    @NotNull
    @Column(unique = true)
    private String name;
    @NotNull
    private double price;
    @NotNull
    private int stock;
    @Column(name="base64", columnDefinition = "LONGTEXT")
    private String base64;

    // Constructor
    public Candy() {

    }
    public Candy(int id){
        this.id = id;
    }



    // getters + settters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int quantity) {
        this.stock = quantity;
    }
    public String getBase64() {
        return base64;
    }
    public void setBase64(String base64) {
        this.base64 = base64;
    }


    // Comparable
    @Override
    public int compareTo(Candy candy) {
        return this.name.compareTo(candy.name);
    }





}

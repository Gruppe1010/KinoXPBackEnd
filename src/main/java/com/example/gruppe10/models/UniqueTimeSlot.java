package com.example.gruppe10.models;

import com.example.gruppe10.models.users.Customer;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="unique_time_slots")
public class UniqueTimeSlot {

    //attributter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name="id_unique_time_slot")
    private int id;
    @NotNull
    @Column(unique = true)
    private String uniqueTimeSlot;

    // TODO Gør så denne ikke bliver hentet med!
    @ManyToOne
    @JoinColumn(name="id_movie", nullable = false)
    private Movie movie;
    @Transient
    private int idMovie;

    // constructors
    public UniqueTimeSlot() {
    }
    public UniqueTimeSlot(int id) {
        this.id = id;
    }


    // getters + setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUniqueTimeSlot() {
        return uniqueTimeSlot;
    }
    public void setUniqueTimeSlot(String uniqueTimeSlot) {
        this.uniqueTimeSlot = uniqueTimeSlot;
    }
    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public int getIdMovie() {
        return idMovie;
    }
    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    @Override
    public String toString() {
        return "UniqueTimeSlot{" +
                "id=" + id +
                ", uniqueTimeSlot='" + uniqueTimeSlot + '\'' +
                ", movie=" + movie.getId() +
                '}';
    }







}

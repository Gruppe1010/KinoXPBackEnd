package com.example.gruppe10.models;

import com.example.gruppe10.models.users.Customer;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="unique_time_slots")
public class UniqueTimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name="id_unique_time_slot")
    private int id;
    @NotNull
    @Column(unique = true)
    private String uniqueTimeSlot;

    @ManyToOne
    @JoinColumn(name="id_movie", nullable = false)
    private Movie movie;

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

    @Override
    public String toString() {
        return "UniqueTimeSlot{" +
                "id=" + id +
                ", uniqueTimeSlot='" + uniqueTimeSlot + '\'' +
                ", movie=" + movie +
                '}';
    }
}

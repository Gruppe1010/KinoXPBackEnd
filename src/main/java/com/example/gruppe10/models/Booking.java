package com.example.gruppe10.models;

import com.example.gruppe10.models.users.Customer;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_booking")
    private int id;
    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private Customer customer;
    @OneToMany
    @JoinColumn(name="id_seat")
    private Set<Seat> seatSet;
    
    public Booking() {
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Set<Seat> getSeatSet() {
        return seatSet;
    }
    public void setSeatSet(Set<Seat> seatSet) {
        this.seatSet = seatSet;
    }
    
    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", customer=" + customer +
                '}';
    }
}

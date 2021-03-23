package com.example.gruppe10.models;

import com.example.gruppe10.models.users.Customer;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="seats")
public class Seat {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_seat")
    private int id;
    @NotNull
    private String rowAndSeat;
    @ManyToOne
    @JoinColumn(name="id_booking", nullable = false)
    private Booking booking;
    
    public Seat() {
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getRowAndSeat() {
        return rowAndSeat;
    }
    public void setRowAndSeat(String rowAndSeat) {
        this.rowAndSeat = rowAndSeat;
    }
    public Booking getBooking() {
        return booking;
    }
    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    
    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", rowAndSeat='" + rowAndSeat + '\'' +
                ", booking=" + booking.getId() +
                '}';
    }
}

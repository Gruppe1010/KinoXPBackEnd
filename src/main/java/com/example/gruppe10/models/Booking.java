package com.example.gruppe10.models;

import com.example.gruppe10.models.users.Customer;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="bookings",uniqueConstraints=@UniqueConstraint(columnNames={"id_booking"}))
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name="id_booking")
    private int id;

    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private Customer customer;

}

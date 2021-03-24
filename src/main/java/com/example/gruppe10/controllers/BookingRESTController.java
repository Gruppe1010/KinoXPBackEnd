package com.example.gruppe10.controllers;
import com.example.gruppe10.models.Booking;
import com.example.gruppe10.models.Seat;
import com.example.gruppe10.models.users.Customer;
import com.example.gruppe10.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin(value = "*")
public class BookingRESTController {


    @Autowired
    BookingRepository bookingRepository;

    // http://localhost:8080/bookings`
    @PostMapping("/bookings")
    public Booking createNewBooking(@RequestBody Customer customer){

        Booking newBooking = new Booking();
        newBooking.setCustomer(customer);

        return bookingRepository.save(newBooking);
    }






}


package com.example.gruppe10.controllers;

import com.example.gruppe10.models.Movie;
import com.example.gruppe10.models.Seat;
import com.example.gruppe10.models.UniqueTimeSlot;
import com.example.gruppe10.repositories.MovieRepository;
import com.example.gruppe10.repositories.SeatRepository;
import com.example.gruppe10.repositories.UniqueTimeSlotRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;

@RestController
@CrossOrigin(value = "*")
public class SeatRESTController {


    @Autowired
    SeatRepository seatRepository;

    // http://localhost:8080/seats/id-unique-time-slot/${chosenTimeSlot.id}`
    @GetMapping("/seats/id-unique-time-slot/{idUniqueTimeSlot}")
    public Set<Seat> findBookedSeatsFromIdUniqueTimeSlot(@PathVariable int idUniqueTimeSlot){

        Set<Seat> seats = seatRepository.findBookedSeatsFromIdUniqueTimeSlot(idUniqueTimeSlot);

        for(Seat s : seats){
            s.setBooking(null);
            s.setUniqueTimeSlot(null);
        }

        return seats;
    }

    //'http://localhost:8080/seats';
    @PostMapping("/seats")
    public Set<Seat> createSeats(@RequestBody Seat[] seats){

        Set<Seat> seatSet = new HashSet<>();

        for(Seat s : seats){
            seatSet.add(s);
            seatRepository.save(s);
        }

        return seatSet;
    }






}


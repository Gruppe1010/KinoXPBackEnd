package com.example.gruppe10.controllers;

import com.example.gruppe10.models.Movie;
import com.example.gruppe10.models.UniqueTimeSlot;
import com.example.gruppe10.repositories.MovieRepository;
import com.example.gruppe10.repositories.UniqueTimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(value = "*")
public class UniqueTimeSlotRESTController {


    @Autowired
    UniqueTimeSlotRepository uniqueTimeSlotRepository;



    //movies?year=${year}&month=${month}
    @GetMapping("/uniqueTimeSlots")
    public List<UniqueTimeSlot> findBookedTimeSlots(@RequestParam("year") int year, @RequestParam("month") int month){

        String likeString = "%year" + year + "month" + month + "%"; // String.format("\%year%dmonth%d%", year, month);
        System.out.println("likeString : " + likeString);

        List<UniqueTimeSlot> uniqueTimeSlots = (List<UniqueTimeSlot>) uniqueTimeSlotRepository.findAll();

        // List<UniqueTimeSlot> uniqueTimeSlots = (List<UniqueTimeSlot>) uniqueTimeSlotRepository.findBookedTimeSlots(likeString);

        System.out.println("uniqueTimeSlots: " + uniqueTimeSlots);

        return uniqueTimeSlots;
    }



}


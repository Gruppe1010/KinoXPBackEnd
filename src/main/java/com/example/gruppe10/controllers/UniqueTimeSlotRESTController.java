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
    public Set<UniqueTimeSlot> findBookedTimeSlots(@RequestParam("year") int year, @RequestParam("month") int month){

        String monthString = Integer.toString(month);

        if(month < 10){
            monthString = "0" + monthString;
        }

        String likeString = "%year" + year + "month" + monthString + "%"; // String.format("\%year%dmonth%d%", year, month);


        Set<UniqueTimeSlot> uniqueTimeSlots = uniqueTimeSlotRepository.findBookedTimeSlots(likeString);

        System.out.println("uniqueTimeSlots: " + uniqueTimeSlots);

        return uniqueTimeSlots;
    }



}


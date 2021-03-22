package com.example.gruppe10.controllers;

import com.example.gruppe10.models.Movie;
import com.example.gruppe10.models.UniqueTimeSlot;
import com.example.gruppe10.repositories.MovieRepository;
import com.example.gruppe10.repositories.UniqueTimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;

@RestController
@CrossOrigin(value = "*")
public class UniqueTimeSlotRESTController {


    @Autowired
    UniqueTimeSlotRepository uniqueTimeSlotRepository;
    /*
    @Autowired
    MovieRepository movieRepository;

     */



    //movies?year=${year}&month=${month}
    @GetMapping("/uniqueTimeSlots")
    public Set<String> findBookedTimeSlots(@RequestParam("year") int year, @RequestParam("month") int month){

        String monthString = Integer.toString(month);
        if(month < 10){
            monthString = "0" + monthString;
        }

        String likeString = "%year" + year + "month" + monthString + "%"; // String.format("\%year%dmonth%d%", year, month);
        Set<UniqueTimeSlot> uniqueTimeSlots = uniqueTimeSlotRepository.findBookedTimeSlots(likeString);

        // tilføjelse efter i går
        Set<String> stringSet = new HashSet<>();

        for(UniqueTimeSlot u : uniqueTimeSlots){
            stringSet.add(u.getUniqueTimeSlot());
        }

        System.out.println("uniqueTimeSlots: " + uniqueTimeSlots);
        return stringSet;
    }
    /*
    public Set<UniqueTimeSlot> findBookedTimeSlots(@RequestParam("year") int year, @RequestParam("month") int month){

        String monthString = Integer.toString(month);

        if(month < 10){
            monthString = "0" + monthString;
        }

        String likeString = "%year" + year + "month" + monthString + "%"; // String.format("\%year%dmonth%d%", year, month);

        Set<UniqueTimeSlot> uniqueTimeSlots = (Set<UniqueTimeSlot>) uniqueTimeSlotRepository.findBookedTimeSlots(likeString);


        return uniqueTimeSlots;
    }

     */

    /*
    @PostMapping(value="/unique-time-slots", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public List<UniqueTimeSlot> createTimeSlots(@RequestBody UniqueTimeSlot[] array){

        List<UniqueTimeSlot> list = new ArrayList<>();


        for(UniqueTimeSlot u : array){

            //u.setMovie(movieRepository.findById(u.getIdMovie()).get());

            uniqueTimeSlotRepository.save(u);
            list.add(u);
            //System.out.println(u);
        }

        System.out.println(list);

        return list;

        //return uniqueTimeSlotRepository.save(list);

    }

     */






}

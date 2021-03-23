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

    @Autowired
    MovieRepository movieRepository;





    //movies?year=${year}&month=${month}
    @GetMapping("/unique-time-slots")
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

        return stringSet;
    }



    /*
    * The @RequestBody annotation allows us to retrieve the request's body.
    * We can then return it as a String or deserialize it into a Plain Old Java Object (POJO).
    * https://stackabuse.com/get-http-post-body-in-spring/
    * */
    @PostMapping(value="/unique-time-slots", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTimeSlots(@RequestBody UniqueTimeSlot[] array){

        List<UniqueTimeSlot> list = new ArrayList<>();

        Movie movie = movieRepository.findById(array[0].getIdMovie()).get();


        for(UniqueTimeSlot u : array){

            /* i vores fetch sætter vi idMovie-attributten
            Her henter vi så det rigtige movie-obj ud fra id'et og sætter det på UniqueTimeSlot's movie-attribut
            Det SKAL vi gøre, fordi den har oneToMany-relationen og via movie-attributten danner id_movie-kolonnen i UniqueTimeSlots tabel
             */


            u.setMovie(movie);

            System.out.println(u);
            uniqueTimeSlotRepository.save(u);

            list.add(u);
            System.out.println(u);
        }

/*
        // vi fjerner movie'en igen fordi vi skal sende listen tilbage, og vi kommer til at få en uendelig loop
        u.setMovie(null);

 */

        System.out.println(list);

        // return list;

        //return uniqueTimeSlotRepository.save(list);

    }










}


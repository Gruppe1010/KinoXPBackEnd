package com.example.gruppe10.controllers;

import com.example.gruppe10.models.Movie;
import com.example.gruppe10.models.UniqueTimeSlot;
import com.example.gruppe10.repositories.MovieRepository;
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
public class UniqueTimeSlotRESTController {


    @Autowired
    UniqueTimeSlotRepository uniqueTimeSlotRepository;

    @Autowired
    MovieRepository movieRepository;



 // const url = `http://localhost:8080/unique-time-slots?idMovie=${movie.id}`;


    //movies?year=${year}&month=${month}
    @GetMapping("/unique-time-slots")
    public Set<UniqueTimeSlot> findBookedTimeSlots(@RequestParam("year") int year, @RequestParam("month") int month){

        String monthString = Integer.toString(month);
        if(month < 10){
            monthString = "0" + monthString;
        }

        String likeString = "%year" + year + "month" + monthString + "%"; // String.format("\%year%dmonth%d%", year, month);
        Set<UniqueTimeSlot> uniqueTimeSlots = uniqueTimeSlotRepository.findBookedTimeSlots(likeString);

        for(UniqueTimeSlot u : uniqueTimeSlots){
            u.setMovie(null);
        }
        // tilføjelse efter i går
        //Set<String> stringSet = new HashSet<>();
        /*
        for(UniqueTimeSlot u : uniqueTimeSlots){
            stringSet.add(u.getUniqueTimeSlot());
        }
         */

        return uniqueTimeSlots;
    }

    @GetMapping("/unique-time-slots/id-movie/{idMovie}")
    public Set<String> findBookedTimeSlotsFromMovieId(@PathVariable int idMovie){
        
        Set<UniqueTimeSlot> uniqueTimeSlots = uniqueTimeSlotRepository.findBookedTimeSlotsFromMovieId(idMovie);
        
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

        // fordi vi alle de timeslots vi har fået ind skal tilknyttes den samme movie, henter vi den ud her, før forloopet
        int idMovie = array[0].getIdMovie();
        Movie movie = movieRepository.findById(idMovie).get();

        //year2021month03week3day4 // bio
        // 20210334

        int earliestDate = 999999999;

        for(UniqueTimeSlot u : array){
            /* i vores fetch sætter vi idMovie-attributten
            Her henter vi så det rigtige movie-obj ud fra id'et og sætter det på UniqueTimeSlot's movie-attribut
            Det SKAL vi gøre, fordi den har oneToMany-relationen og via movie-attributten danner id_movie-kolonnen i UniqueTimeSlots tabel
             */
            u.setMovie(movie);
            uniqueTimeSlotRepository.save(u);

            // Her finder vi premieredatoen for filmen vi lige har oprettet,
            // ved at finde ud af hvilket af de valgte timeslot som er tidligst

            // den er 8 lang - fordi den også tager row og bio-tallene med
            String allNumbers = u.getUniqueTimeSlot().replaceAll("\\D+","");
            // vi fjerner de sidste to tal, så vi kun får år, måned, week, dag
            String realString = allNumbers.substring(0, allNumbers.length()-2);

            int dateInt = Integer.parseInt(realString);

            if(dateInt < earliestDate){
                earliestDate = dateInt;
            }
        }

        // vi laver nu earliestDate
        // "yyyy-mm-dd"
        String earliestDateString = Integer.toString(earliestDate);

        int year = Integer.parseInt(earliestDateString.substring(0, 4));
        int month = Integer.parseInt(earliestDateString.substring(4, 6));
        int week = Integer.parseInt(earliestDateString.substring(6, 7));
        int day = Integer.parseInt(earliestDateString.substring(7, 8));

        // https://www.baeldung.com/java-date-to-localdate-and-localdatetime
        Date premiere = java.sql.Date.valueOf(getDate(week, day, month, year));


        movieRepository.updateMoviePremiere(idMovie, premiere);

    }
    
    public LocalDate getDate(int weekOfMonth, int dayOfWeek, int month, int year) {
        // you can customize your week definition (first day of week and mininum days in first week)
        WeekFields wf = WeekFields.of(DayOfWeek.SUNDAY, 2);

        // Sunday is 0, adjusting value
        DayOfWeek dow = DayOfWeek.of(dayOfWeek == 0 ? 7 : dayOfWeek);

        // get the first weekday of the month
        LocalDate first = LocalDate.of(year, month, 1).with(TemporalAdjusters.nextOrSame(dow));

        // check in which week this date is
        int week = first.get(wf.weekOfMonth());

        // adjust to weekOfMonth
        return first.plusWeeks(weekOfMonth - week);

    }








}


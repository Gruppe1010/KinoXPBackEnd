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
            u.setSeatSet(null);
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
    public Set<UniqueTimeSlot> findBookedTimeSlotsFromMovieId(@PathVariable int idMovie){
        
        Set<UniqueTimeSlot> uniqueTimeSlots = uniqueTimeSlotRepository.findBookedTimeSlotsFromMovieId(idMovie);
        System.out.println(uniqueTimeSlots);
       
        for(UniqueTimeSlot u : uniqueTimeSlots){
            u.setMovie(null);
            u.setSeatSet(null);
        }
        System.out.println(uniqueTimeSlots);
       
        return uniqueTimeSlots;
    }


    /*
    * The @RequestBody annotation allows us to retrieve the request's body.
    * We can then return it as a String or deserialize it into a Plain Old Java Object (POJO).
    * https://stackabuse.com/get-http-post-body-in-spring/
    * */
    /*Denne postMapping gør 2 ting:
    Den tilføjer nye UniqueTimeSlot-rækker i db
    OG den opdaterer premiere-kolonnen i movie
     */

    @PostMapping(value="/unique-time-slots", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTimeSlots(@RequestBody UniqueTimeSlot[] array){

        // fordi vi alle de timeslots vi har fået ind skal tilknyttes den samme movie, henter vi den ud her, før forloopet
        int idMovie = array[0].getIdMovie();
        Movie movie = movieRepository.findById(idMovie).get();

        int earliestDate = 999999999;

        for(UniqueTimeSlot u : array){
            /* i vores fetch sætter vi idMovie-attributten
            Her henter vi så det rigtige movie-obj ud fra id'et og sætter det på UniqueTimeSlot's movie-attribut
            Det SKAL vi gøre, fordi den har oneToMany-relationen og via movie-attributten danner id_movie-kolonnen i UniqueTimeSlots tabel
             */
            u.setMovie(movie);
            u.setDate(convertDateStringToDate(deleteCharsFromUniqueTimeSlot(u.getUniqueTimeSlot())));


            uniqueTimeSlotRepository.save(u);

            // Her finder vi premieredatoen for filmen vi lige har oprettet,
            // ved at finde ud af hvilket af de valgte timeslot som er tidligst

            //year2021month03week3day4 // row?bio?
            // 20210334
            // Vi laver uniqueTimeSlot-string-attributten om til KUN tal - minus de to sidste, som står for række og bio
            String realString = deleteCharsFromUniqueTimeSlot(u.getUniqueTimeSlot());

            int dateInt = Integer.parseInt(realString);

            if(dateInt < earliestDate){
                earliestDate = dateInt;
            }
        }

        // vi laver nu earliestDate
        // "yyyy-mm-dd"
        String earliestDateString = Integer.toString(earliestDate);

        Date premiere = convertDateStringToDate(earliestDateString);

        movieRepository.updateMoviePremiere(idMovie, premiere);
    }

    public Date convertDateStringToDate(String dateString){

        int year = Integer.parseInt(dateString.substring(0, 4));
        int month = Integer.parseInt(dateString.substring(4, 6));
        int week = Integer.parseInt(dateString.substring(6, 7));
        int day = Integer.parseInt(dateString.substring(7, 8));

        // https://www.baeldung.com/java-date-to-localdate-and-localdatetime
        return java.sql.Date.valueOf(getDate(week, day, month, year));
    }

    public String deleteCharsFromUniqueTimeSlot(String uniqueTimeSlot){

        // Vi laver uniqueTimeSlot-string-attributten om til KUN tal
        // den er 8 lang - fordi den også tager row og bio-tallene med
        String allNumbers = uniqueTimeSlot.replaceAll("\\D+","");
        // vi fjerner de sidste to tal, så vi kun får år, måned, week, dag
        return allNumbers.substring(0, allNumbers.length()-2);

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


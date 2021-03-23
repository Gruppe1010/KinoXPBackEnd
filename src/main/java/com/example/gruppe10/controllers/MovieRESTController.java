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
public class MovieRESTController {
    
    @Autowired
    MovieRepository movieRepository;
    
    @GetMapping("/movies")
    public ArrayList<Movie> findActiveMovies(){
        Optional<ArrayList<Movie>> optionalActiveMovieList = Optional.ofNullable(movieRepository.findMovieByActive(true));
        if(optionalActiveMovieList.isPresent()) {
            ArrayList<Movie> movieArrayList = optionalActiveMovieList.get();

            for(Movie movie : movieArrayList){
                movie.setUniqueTimeSlots(null);
            }

            Collections.sort(movieArrayList);
            
            return movieArrayList;
        }
        return new ArrayList<>();
    }


    // til når vi opretter en movie
    @PostMapping(value="/movies", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie postMovie(@RequestBody Movie movie){
        // vi tjekker om der allerede findes en movie med titlen, fordi der må kun findes én movie med titlen
        Optional<Movie> optionalMovie = Optional.ofNullable(movieRepository.findByTitle(movie.getTitle()));

        System.out.println(movie.getPremiere());

        if(optionalMovie.isEmpty()){
            System.out.println("der er ikke nogen i db");
            return movieRepository.save(movie);
        }

        return new Movie(0);
    }



}


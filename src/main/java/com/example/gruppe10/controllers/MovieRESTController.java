package com.example.gruppe10.controllers;

import com.example.gruppe10.models.Movie;
import com.example.gruppe10.repositories.CustomerRepository;
import com.example.gruppe10.repositories.MovieRepository;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

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
            
            Collections.sort(movieArrayList);

            /*
            String jsonText = JSONValue.toJSONString(movieArrayList);
            System.out.println(jsonText);

             */


            return movieArrayList;
        }
        return new ArrayList<>();
    }
}


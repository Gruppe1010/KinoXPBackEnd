package com.example.gruppe10.controllers;

import com.example.gruppe10.models.Movie;
import com.example.gruppe10.repositories.CustomerRepository;
import com.example.gruppe10.repositories.MovieRepository;
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
        return movieRepository.findMovieByActive(true);
        
        
        
        
        /*Optional<ArrayList<Movie>> optionalActiveMovieList = Optional.ofNullable(movieRepository.findMovieByActive(true));
        if(optionalActiveMovieList.isPresent()) {
    
            ArrayList<Movie> movieArrayList = optionalActiveMovieList.get();
    
            List<Movie> filteredList = optionalActiveMovieList.stream() .filter(Optional::isPresent) .map(Optional::get) .collect(Collectors.toList());
            
            return Collections.sort(movieArrayList);
        }
        return new ArrayList<Movie>();*/
    }
}


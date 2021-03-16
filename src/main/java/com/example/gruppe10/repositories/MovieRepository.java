package com.example.gruppe10.repositories;

import com.example.gruppe10.models.Movie;
import com.example.gruppe10.models.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    
    @Query("SELECT movies FROM Movie movies WHERE movies.active = ?1")
    ArrayList<Movie> findMovieByActive(boolean active);
    
}

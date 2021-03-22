package com.example.gruppe10.repositories;

import com.example.gruppe10.models.Movie;
import com.example.gruppe10.models.UniqueTimeSlot;
import com.example.gruppe10.models.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

// <Type, typePåPrimaryKey>, så fordi id'et er en int skal det være Integer
public interface UniqueTimeSlotRepository extends JpaRepository<UniqueTimeSlot, Integer> {

    /*
    @Query("SELECT movies FROM Movie movies WHERE movies.active = ?1")
    ArrayList<Movie> findMovieByActive(boolean active);


    @Query("SELECT movies FROM Movie movies WHERE movies.title = ?1")
    Movie findByTitle(String title);

     */


    // "SELECT s FROM Student s JOIN s.skillTags t WHERE t.name = LOWER(:tagName) AND t.value > :tagValue")

    @Query("SELECT uniqueTimeSlot FROM UniqueTimeSlot uniqueTimeSlot WHERE uniqueTimeSlot.uniqueTimeSlot LIKE ?1")
    Set<UniqueTimeSlot> findBookedTimeSlots(String likeString);



}

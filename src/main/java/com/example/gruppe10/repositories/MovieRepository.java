package com.example.gruppe10.repositories;

import com.example.gruppe10.models.Movie;
import com.example.gruppe10.models.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

// <Type, typePåPrimaryKey>, så fordi id'et er en int skal det være Integer
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    
    @Query("SELECT movies FROM Movie movies WHERE movies.active = ?1")
    ArrayList<Movie> findMovieByActive(boolean active);


    @Query("SELECT movies FROM Movie movies WHERE movies.title = ?1")
    Movie findByTitle(String title);


    // "SELECT s FROM Student s JOIN s.skillTags t WHERE t.name = LOWER(:tagName) AND t.value > :tagValue")

    @Query("SELECT movie FROM Movie movie JOIN movie.uniqueTimeSlots u WHERE u LIKE :likeString")
    String findBookedTimeSlots(String likeString);

    @Transactional
    @Modifying
    @Query("UPDATE Movie movie SET movie.premiere = ?2 WHERE movie.id = ?1")
    void updateMoviePremiere(int idMovie, Date premiere);

    /*
    * UPDATE table_name
      SET column1 = value1, column2 = value2, ...
      WHERE condition;
    *
    *
    *
    * @Query("UPDATE Company c SET c.address = :address WHERE c.id = :companyId")
    * */




    
    //@Query("SELECT unique FROM Movie unique INNER JOIN unique.uniqueTimeSlots p WHERE KEY() ")
    
}

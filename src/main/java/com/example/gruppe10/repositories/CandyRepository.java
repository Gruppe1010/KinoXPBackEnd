package com.example.gruppe10.repositories;

import com.example.gruppe10.models.Candy;
import com.example.gruppe10.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// <Type, typePåPrimaryKey>, så fordi id'et er en int skal det være Integer
public interface CandyRepository extends JpaRepository<Candy, Integer> {


    @Query("SELECT candy FROM Candy candy WHERE candy.name = ?1")
    Candy findByName(String name);



}

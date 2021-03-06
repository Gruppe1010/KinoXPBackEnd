package com.example.gruppe10.controllers;

import com.example.gruppe10.models.Candy;
import com.example.gruppe10.models.Movie;
import com.example.gruppe10.models.users.Customer;
import com.example.gruppe10.repositories.CandyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "*")
public class CandyRESTController {

    @Autowired
    private CandyRepository candyRepository;


    @GetMapping("/candy")
    //@ResponseStatus(HttpStatus.FOUND)
    public ArrayList<Candy> findAllCandy(){

        ArrayList<Candy> candyList = (ArrayList<Candy>) candyRepository.findAll();

        Collections.sort(candyList);

        System.out.println(candyList);

        return candyList;
    }


    @PostMapping(value="/candy", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Candy postCandy(@RequestBody Candy candy){
        // vi tjekker om der allerede findes en movie med titlen, fordi der må kun findes én movie med titlen
        Optional<Candy> optionalCandy = Optional.ofNullable(candyRepository.findByName(candy.getName()));

        if(optionalCandy.isEmpty()){
            System.out.println("der er ikke nogen i db");
            return candyRepository.save(candy);
        }

        return new Candy(0);
    }
    
    @PutMapping(value="/candy", consumes = "application/json")
    public void updateCandy(@RequestBody Candy candy){
        
        Optional<Candy> tempCandy = candyRepository.findById(candy.getId());
        
        if(tempCandy.isPresent()){
    
            Candy updateCandy = tempCandy.get();
            
            updateCandy.setPrice(candy.getPrice());
            updateCandy.setStock(candy.getStock());
            candyRepository.save(updateCandy);
        }
    }
    
    @DeleteMapping(value="/candy", consumes = "application/json")
    public void deleteCandy(@RequestBody Candy candy){
        candyRepository.deleteById(candy.getId());
    }
    




}

package com.example.gruppe10.controllers;

import com.example.gruppe10.models.users.Customer;
import com.example.gruppe10.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
public class CustomerRESTController {

    // Autowired implementerer interfacet og opretter en instans af implementationen som gemmes i customerRepository
    @Autowired
    CustomerRepository customerRepository;




    // nu skal den indsætte en person i vores db
    /* Han vil have at vi bruger postman her til at teste denne postmapping

        I postmand == GET --> skriv en url der giver en list<Person> tilbage --> body --> raw --> JSON --> JSON og så send
        Nu vil han have at vi kopierer det som står nederst op i den øverste tekststed
     */
    @PostMapping(value="/customers", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer postPerson(@RequestBody Customer customer){

        // den gør at personen bliver gemt ned i repository'et
        // save == TROR den kigger på om den er der i forvejen
        return customerRepository.save(customer);
    }



}

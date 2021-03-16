package com.example.gruppe10.controllers;

import com.example.gruppe10.models.users.Customer;
import com.example.gruppe10.models.users.User;
import com.example.gruppe10.repositories.CustomerRepository;
import com.example.gruppe10.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(value = "*")
public class CustomerRESTController {

    // Autowired implementerer interfacet og opretter en instans af implementationen som gemmes i customerRepository
    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired
    UserRepository userRepository;
    
    // nu skal den indsætte en person i vores db
    /* Han vil have at vi bruger postman her til at teste denne postmapping

        I postmand == GET --> skriv en url der giver en list<Person> tilbage --> body --> raw --> JSON --> JSON og så send
        Nu vil han have at vi kopierer det som står nederst op i den øverste tekststed
     */
    @PostMapping(value="/customers", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    //TODO Hvorfor retunere vi noget i POST
    public Customer postCustomer(@RequestBody Customer customer){
        // den gør at personen bliver gemt ned i repository'et
        // save == TROR den kigger på om den er der i forvejen

        Optional<Customer> optionalCustomer = Optional.ofNullable(customerRepository.findByEmail(customer.getEmail()));

        if(optionalCustomer.isEmpty()){
            System.out.println("der er ikke nogen i db");
            return customerRepository.save(customer);
        }

        return new Customer(0);

    }
    
    @GetMapping("/customers")
    public User findUser(@RequestParam("email") String email, @RequestParam("password") String password){
    
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmailAndPassword(email, password));
        
        return optionalUser.orElseGet(() -> new User(0));
    }
    
}

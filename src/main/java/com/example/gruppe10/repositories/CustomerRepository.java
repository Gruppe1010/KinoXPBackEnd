package com.example.gruppe10.repositories;

import com.example.gruppe10.models.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// <Type, typePåPrimaryKey>, så fordi id'et er en int skal det være Integer
public interface CustomerRepository extends JpaRepository<Customer, Integer> {



    // @Query("FROM Users WHERE email = ?1")

    @Query("SELECT customer FROM Customer customer WHERE customer.email = ?1")
    Customer findByEmail(String email);


}

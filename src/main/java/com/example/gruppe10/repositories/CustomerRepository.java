package com.example.gruppe10.repositories;

import com.example.gruppe10.models.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}

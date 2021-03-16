package com.example.gruppe10.repositories;

import com.example.gruppe10.models.users.Customer;
import com.example.gruppe10.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    @Query("SELECT user FROM User user WHERE user.email = ?1 AND user.password = ?2")
    User findByEmailAndPassword(String email, String password);
    
    
}

package com.example.gruppe10.models.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
// @DiscriminatorColumn(columnDefinition = "Staff")
public class Admin extends User{
    
    public Admin() {
    }
    
    public Admin(int id, String name, String email, String password) {
        super(id, name, email, password);
    }
}

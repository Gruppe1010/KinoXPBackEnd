package com.example.gruppe10.models.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Staff")
public class Staff extends User {
    
    public Staff() {}
    
    public Staff(int id, String name, String email, String password) {
        super(id, 2, name, email, password);
    }
}

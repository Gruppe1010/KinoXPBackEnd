package com.example.gruppe10.models.users;

public class Admin extends User{
    
    public Admin() {
    }
    
    public Admin(int id, int type, String name, String email, String password) {
        super(id, type, name, email, password);
    }
}

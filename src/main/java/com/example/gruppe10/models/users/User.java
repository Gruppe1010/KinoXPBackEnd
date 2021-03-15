package com.example.gruppe10.models.users;

public class User {
    // attributter
    private int id;
    private int type;
    private String name;
    private String email;
    private String password;

    // constructors

    public User() {
    }

    public User(int id, int type, String name, String email, String password) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.email = email;
        this.password = password;
    }


    // other stuff
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    // getters + setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

package com.example.gruppe10.models.users;


import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
// siger vi for at lægge alle User-klasser i én tabel
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

@Table(name="users",uniqueConstraints=@UniqueConstraint(columnNames={"id_user","email"}))

public class User {
    // attributter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name="id_user")
    private int id;
    @NotNull
    private int type;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
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

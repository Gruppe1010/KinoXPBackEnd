package com.example.gruppe10.models.users;


import com.sun.istack.NotNull;

import javax.persistence.*;

/*
@Entity == vi laver en entitet som skal lægges i db (en ny tabel)


@Inheritance(strategy = InheritanceType.SINGLE_TABLE) ==
    siger vi for at lægge alle User-klasser i én tabel - pga. nedarv
    Når man har flere entities i én tabel, opretter den automatisk en dtype kolonne (datatype)
    Derfor skriver vi fx @DiscriminatorValue("staff") i Staff-klassen som nedarver fra User, for at komme værdien "staff"
    ind i dtype kolonnen.

Med @DiscriminatorColumn(name = "type") ==
    Vi omdøber dtype-kolonnens navn til type

@Table(name="users",uniqueConstraints=@UniqueConstraint(columnNames={"id_user","email"})) ==

*/

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

@Table(name="users") // vi kan også sætte UQ kolonner her: uniqueConstraints=@UniqueConstraint(columnNames={"email"})))

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
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;

    // constructors
    public User() {
    }

    public User(int id) {
        this.id = id;
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

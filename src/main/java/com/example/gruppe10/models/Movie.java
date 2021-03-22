package com.example.gruppe10.models;

import com.example.gruppe10.models.users.Customer;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="movies")
public class Movie implements Comparable<Movie>{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name="id_movie")
    private int id;
    @NotNull
    private boolean active;
    @NotNull
    @Column(unique = true)
    private String title;
    // TODO tilføj premieredag
    @Temporal(TemporalType.DATE) //Ændrer typen fra DateTime til Date i MySQL databasen
    private Date premiere;
    @NotNull
    private int yearOfRelease;
    @NotNull
    private String length;
    @NotNull
    private int ageLimit;
    @OneToMany(mappedBy="movie")
    private Set<UniqueTimeSlot> uniqueTimeSlots;
    @NotNull
    @Column(name="base64", columnDefinition = "LONGTEXT")
    private String base64;
    
    public Movie() {
    }

    public Movie(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getPremiere() {
        return premiere;
    }
    public void setPremiere(Date premiereDate) {
        this.premiere = premiereDate;
    }
    public int getYearOfRelease() {
        return yearOfRelease;
    }
    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }
    public String getLength() {
        return length;
    }
    public void setLength(String length) {
        this.length = length;
    }
    public int getAgeLimit() {
        return ageLimit;
    }
    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }
    public Set<UniqueTimeSlot> getUniqueTimeSlots() {
        return uniqueTimeSlots;
    }
    public void setUniqueTimeSlots(Set<UniqueTimeSlot> uniqueTimeSlots) {
        this.uniqueTimeSlots = uniqueTimeSlots;
    }
    public String getBase64() {
        return base64;
    }
    public void setBase64(String base64) {
        this.base64 = base64;
    }



    @Override
    public int compareTo(Movie o) {
        if(premiere.before(o.getPremiere())){
            return -1;
        } else if(premiere.after(o.getPremiere())){
            return 1;
        }
        return 0;
    }
}

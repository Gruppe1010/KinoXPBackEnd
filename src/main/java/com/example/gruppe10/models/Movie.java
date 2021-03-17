package com.example.gruppe10.models;

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
    @NotNull
    @Temporal(TemporalType.DATE) //Ændrer typen fra DateTime til Date i MySQL databasen
    private Date premiereDate;
    @NotNull
    private int yearOfRelease;
    @NotNull
    private String length;
    @NotNull
    private int ageLimit;
    @OneToMany(mappedBy = "movie")
    private Set<Showing> show;
    @NotNull
    @Column(name="base64", columnDefinition = "TEXT")
    private String base64;
    
    public Movie() {
    }

    public Movie(int id) {
        this.id = id;
    }

    public Movie(String title, Date premiereDate, int yearOfRelease, String length, int ageLimit, Set<Showing> show, String base64) {
        this.active = true;
        this.title = title;
        this.premiereDate = premiereDate;
        this.yearOfRelease = yearOfRelease;
        this.length = length;
        this.ageLimit = ageLimit;
        this.show = show;
        this.base64 = base64;
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
    public Date getPremiereDate() {
        return premiereDate;
    }
    public void setPremiereDate(Date premiereDate) {
        this.premiereDate = premiereDate;
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
    public Set<Showing> getShow() {
        return show;
    }
    public void setShow(Set<Showing> show) {
        this.show = show;
    }
    public String getBase64() {
        return base64;
    }
    public void setBase64(String base64) {
        this.base64 = base64;
    }
    
    @Override
    public int compareTo(Movie o) {
        if(premiereDate.before(o.getPremiereDate())){
            return -1;
        } else if(premiereDate.after(o.getPremiereDate())){
            return 1;
        }
        return 0;
    }
}

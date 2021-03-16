package com.example.gruppe10.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="movies")
public class Movie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name="id_movie")
    private int id;
    @NotNull
    @Column(unique = true)
    private String title;
    @NotNull
    @Temporal(TemporalType.DATE) //Ændrer typen fra DateTime til Date i MySQL databasen
    private Date premiereDate;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date yearOfRelease;
    @NotNull
    private String length;
    @NotNull
    private int ageLimit;
    @OneToMany(mappedBy = "movie")
    private Set<Showing> show;
    @Column(columnDefinition="LONGBLOB")
    private byte[] moviePoster;
    @Transient
    private String base64;
    
    public Movie() {
    }
    
    public Movie(String title, Date premiereDate, Date yearOfRelease, String length, int ageLimit, Set<Showing> show, byte[] moviePoster) {
        this.title = title;
        this.premiereDate = premiereDate;
        this.yearOfRelease = yearOfRelease;
        this.length = length;
        this.ageLimit = ageLimit;
        this.show = show;
        this.moviePoster = moviePoster;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
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
    
    public Date getYearOfRelease() {
        return yearOfRelease;
    }
    
    public void setYearOfRelease(Date yearOfRelease) {
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
    
    public byte[] getMoviePoster() {
        return moviePoster;
    }
    
    public void setMoviePoster(byte[] moviePoster) {
        this.moviePoster = moviePoster;
    }
    
    public String getBase64() {
        return base64;
    }
    
    public void setBase64(String base64) {
        this.base64 = base64;
    }
}

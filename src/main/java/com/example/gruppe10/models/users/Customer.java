package com.example.gruppe10.models.users;

import com.example.gruppe10.models.Booking;
import com.sun.istack.NotNull;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@DiscriminatorValue("Customer")
public class Customer extends User{

    @OneToMany(mappedBy = "customer") // customer == den attribut vi har lavet i Booking-klassen
    private Set<Booking> bookingList;
    
    //Constructor
    public Customer() {
        super();
    }
    
    public Customer(int id, String name, String email, String password,Set<Booking> bookingList){
        super(id, 1, name, email, password);
        this.bookingList = bookingList;
    }
    
    @Override
    public String toString() {
        return "Customer{" +
                "bookingList=" + bookingList +
                '}';
    }
    
    //Getters og setters
    public Set<Booking> getBookingList() {
        return bookingList;
    }
    public void setBookingList(Set<Booking> bookingList) {
        this.bookingList = bookingList;
    }
    
    
    
  
}

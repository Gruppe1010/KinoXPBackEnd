package com.example.gruppe10.models.users;

import com.example.gruppe10.models.Booking;

import java.util.ArrayList;

public class Customer extends User{
    private ArrayList<Booking> bookingList;
    
    //Constructor
    public Customer() {
        super();
    }
    
    public Customer(int id, int type, String name, String email, String password,ArrayList<Booking> bookingList){
        super(id, type, name, email, password);
        this.bookingList = bookingList;
    }
    
    @Override
    public String toString() {
        return "Customer{" +
                "bookingList=" + bookingList +
                '}';
    }
    
    //Getters og setters
    public ArrayList<Booking> getBookingList() {
        return bookingList;
    }
    public void setBookingList(ArrayList<Booking> bookingList) {
        this.bookingList = bookingList;
    }
    
    
    
  
}

package com.example.gruppe10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        Calendar today = Calendar.getInstance();
        SimpleDateFormat formatterDay = new SimpleDateFormat("dd");
        SimpleDateFormat formatterMonth = new SimpleDateFormat("MM");
        SimpleDateFormat formatterYear = new SimpleDateFormat("yyyy");

        String day = formatterDay.format(today.getTime());
        String month = formatterMonth.format(today.getTime());
        String year = formatterYear.format(today.getTime());


        System.out.println("day: " + day + ", month: " + month + ", year: " + year);
        // knap for at skifte måned: month ++;
    }

}

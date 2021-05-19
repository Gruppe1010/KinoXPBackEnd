package com.example.gruppe10.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TEST
{
    
    
    @GetMapping("/test")
    public String test(){
        return "movies";
    }
    
    
    
}

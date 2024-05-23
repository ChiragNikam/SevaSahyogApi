package com.development.api.SevaSahyog.events.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class Events {

    @GetMapping
    public String getEvents(){
        return "events";
    }
}

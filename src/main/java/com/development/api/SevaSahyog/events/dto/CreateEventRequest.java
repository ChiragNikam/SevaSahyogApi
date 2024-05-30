package com.development.api.SevaSahyog.events.dto;

import com.development.api.SevaSahyog.events.data.Event;

public class CreateEventRequest {
    private String email;
    private Event event;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}

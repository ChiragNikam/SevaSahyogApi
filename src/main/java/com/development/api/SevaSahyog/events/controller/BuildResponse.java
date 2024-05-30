package com.development.api.SevaSahyog.events.controller;

import com.development.api.SevaSahyog.events.data.Event;
import com.development.api.SevaSahyog.events.dto.CreateEventRequest;
import com.development.api.SevaSahyog.events.dto.CreateEventResponse;

public class BuildResponse {

    public CreateEventResponse buildCreateEvent(Event savedEvent){
        CreateEventResponse eventResponse = new CreateEventResponse();
        eventResponse.setEventId(savedEvent.getEventId());
        eventResponse.setName(savedEvent.getName());
        eventResponse.setShortDesc(savedEvent.getShortDesc());
        eventResponse.setLongDesc(savedEvent.getLongDesc());
        eventResponse.setOrganizer(savedEvent.getOrganizer());
        eventResponse.setLocation(savedEvent.getLocation());
        eventResponse.setDd(savedEvent.getDd());
        eventResponse.setMm(savedEvent.getMm());
        eventResponse.setYyyy(savedEvent.getYyyy());

        return eventResponse;
    }
}

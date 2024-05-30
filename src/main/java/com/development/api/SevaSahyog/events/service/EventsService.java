package com.development.api.SevaSahyog.events.service;

import com.development.api.SevaSahyog.events.data.Event;
import com.development.api.SevaSahyog.events.dto.CreateEventRequest;

import java.util.List;

public interface EventsService {

    Event saveEvent(CreateEventRequest eventData);

    List<Event> getEventById(String userId);

    List<Event> getAllEvents();

    Event updateEvent(Event updatedEvent);
}

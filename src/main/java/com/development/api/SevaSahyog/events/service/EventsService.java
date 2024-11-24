package com.development.api.SevaSahyog.events.service;

import com.development.api.SevaSahyog.events.data.Event;
import com.development.api.SevaSahyog.events.dto.CreateEventRequest;

import java.util.List;
import java.util.Set;

public interface EventsService {

    Event saveEvent(CreateEventRequest eventData);

    List<Event> getEventByUserId(String userId);

    List<Event> getAllEvents();

    Event getEventByItsId(long eventId);

    Event updateEvent(Event updatedEvent);

    void deleteEvent(long id);

    List<Event> filterUpcomingEvents(String userId);

    Set<Integer> getEventYears(String userId);

    Event updatedEventWithEventImages(Event eventToUpdate, List<String> eventImages);
}

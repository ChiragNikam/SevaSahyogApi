package com.development.api.SevaSahyog.events.service.eventimpl;

import com.development.api.SevaSahyog.auth.data.NgoAccount;
import com.development.api.SevaSahyog.auth.repo.NgoAccountRepo;
import com.development.api.SevaSahyog.events.data.Event;
import com.development.api.SevaSahyog.events.dto.CreateEventRequest;
import com.development.api.SevaSahyog.events.repo.EventsRepo;
import com.development.api.SevaSahyog.events.service.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EventsServiceImpl implements EventsService {

    private final NgoAccountRepo ngoAccountRepo;
    private final EventsRepo eventsRepo;

    @Override
    public Event saveEvent(CreateEventRequest eventData) {
        Event savedEvent = null;
        Optional<NgoAccount> accountOptional = ngoAccountRepo.findByEmail(eventData.getEmail()); // get ngo account to which events are saved
        if (accountOptional.isPresent()) {
            NgoAccount account = accountOptional.get();
            System.out.println(account.getUserId());
            eventData.getEvent().setNgoAccount(account); // set ngo account to the event for mapping
            savedEvent = eventsRepo.save(eventData.getEvent()); // save event to database
        }
        return savedEvent;
    }

    @Override
    public List<Event> getEventByUserId(String userId) {
        return eventsRepo.findByNgoAccountUserId(userId);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventsRepo.findAll();
    }

    @Override
    public Event updateEvent(Event updateEvent) {

        Optional<Event> oldEventOpt = eventsRepo.findById((long) updateEvent.getEventId());
        Event existingEvent = null;
        if (oldEventOpt.isPresent()) {
            existingEvent = oldEventOpt.get();

            if (updateEvent.getName() != null) existingEvent.setName(updateEvent.getName());
            if (updateEvent.getShortDesc() != null) existingEvent.setShortDesc(updateEvent.getShortDesc());
            if (updateEvent.getLongDesc() != null) existingEvent.setLongDesc(updateEvent.getLongDesc());
            if (updateEvent.getOrganizer() != null) existingEvent.setOrganizer(updateEvent.getOrganizer());
            if (updateEvent.getOrganizerPhone() != null)
                existingEvent.setOrganizerPhone(updateEvent.getOrganizerPhone());
            if (updateEvent.getLocation() != null) existingEvent.setLocation(updateEvent.getLocation());
            if (updateEvent.getDd() != 0) existingEvent.setDd(updateEvent.getDd());
            if (updateEvent.getMm() != 0) existingEvent.setMm(updateEvent.getMm());
            if (updateEvent.getYyyy() != 0) existingEvent.setYyyy(updateEvent.getYyyy());
            if (updateEvent.getStatus() != 0) existingEvent.setStatus(updateEvent.getStatus());

            return eventsRepo.save(existingEvent);
        } else {
            throw new IllegalArgumentException("Unable to update event");
        }

    }

    @Override
    public void deleteEvent(long id) {
        Optional<Event> eventToDeleteOpt = eventsRepo.findById(id);
        if (eventToDeleteOpt.isPresent()) {
            eventsRepo.delete(eventToDeleteOpt.get());
        } else {
            throw new IllegalArgumentException("Event Not Found");
        }
    }

    @Override
    public List<Event> filterUpcomingEvents(String userId) {
        // get all the events to filter
        List<Event> allEvents = eventsRepo.findByNgoAccountUserId(userId);

        List<Event> upcomingEvents = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();
//        int todayDate = currentDate.getDayOfMonth();

        // filter upcoming events
        for (Event currentEvent : allEvents) {

            // Create LocalDate from event's date fields
            LocalDate eventDate = LocalDate.of(currentEvent.getYyyy(), currentEvent.getMm(), currentEvent.getDd());

            if (!eventDate.isBefore(currentDate)) { // add event to list if event date is today or after today
                upcomingEvents.add(currentEvent);
            }
        }

        return upcomingEvents;
    }

    @Override
    public Set<Integer> getEventYears(String userId) {
        // get all the events to filter
        List<Event> allEvents = eventsRepo.findByNgoAccountUserId(userId);

        Set<Integer> eventYears = new HashSet<>();
        
        for(Event currentEvent: allEvents){
            eventYears.add(currentEvent.getYyyy());
        }
        
        return eventYears;
    }

    @Override
    public Event updatedEventWithEventImages(Event eventToUpdate, List<String> eventImages) {
        eventToUpdate.setEventImagesUrls(eventImages);
        return eventToUpdate;
    }

    @Override
    public Event getEventByItsId(long eventId){
        Optional<Event> event = eventsRepo.findById(eventId);
        if (event.isPresent()) {
            return event.get();
        } else {
            throw new IllegalArgumentException("Event Not Found");
        }
    }


}

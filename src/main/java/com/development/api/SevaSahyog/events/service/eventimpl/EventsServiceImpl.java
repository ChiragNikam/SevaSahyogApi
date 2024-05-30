package com.development.api.SevaSahyog.events.service.eventimpl;

import com.development.api.SevaSahyog.auth.data.NgoAccount;
import com.development.api.SevaSahyog.auth.repo.NgoAccountRepo;
import com.development.api.SevaSahyog.events.data.Event;
import com.development.api.SevaSahyog.events.dto.CreateEventRequest;
import com.development.api.SevaSahyog.events.repo.EventsRepo;
import com.development.api.SevaSahyog.events.service.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public List<Event> getEventById(String userId) {
        return eventsRepo.findByNgoAccountUserId(userId);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventsRepo.findAll();
    }

    @Override
    public Event updateEvent(Event updatedEvent) {

        Optional<Event> oldEventOpt = eventsRepo.findById((long) updatedEvent.getEventId());
        Event oldEvent = oldEventOpt.get();
        Event newUpdatedEvent = new Event();
        // check for old data and updated data
        // id
        updatedEvent.setEventId(oldEventOpt.get().getEventId());
        // name
        if (updatedEvent.getName() == null) {
            newUpdatedEvent.setName(oldEvent.getName());
        } else {
            newUpdatedEvent.setName(updatedEvent.getName());
        }
        // short desc
        if (updatedEvent.getShortDesc() == null) {
            newUpdatedEvent.setShortDesc(oldEvent.getShortDesc());
        } else {
            newUpdatedEvent.setShortDesc(updatedEvent.getShortDesc());
        }

        // long desc
        if (updatedEvent.getLongDesc() == null) {
            newUpdatedEvent.setLongDesc(oldEvent.getLongDesc());
        } else {
            newUpdatedEvent.setLongDesc(updatedEvent.getLongDesc());
        }

        // organizer
        if (updatedEvent.getOrganizer() == null) {
            newUpdatedEvent.setOrganizer(oldEvent.getOrganizer());
        } else {
            newUpdatedEvent.setOrganizer(updatedEvent.getOrganizer());
        }

        // location
        if (updatedEvent.getLocation() == null) {
            newUpdatedEvent.setLocation(oldEvent.getLocation());
        } else {
            newUpdatedEvent.setLocation(updatedEvent.getLocation());
        }

        // dd
//        if (updatedEvent.getDd() == null){
//            newUpdatedEvent.setShortDesc(oldEvent.getShortDesc());
//        } else {
//            newUpdatedEvent.setShortDesc(updatedEvent.getShortDesc());
//        }

        // mm

        // yyyy

        // status

        // ngo account
        newUpdatedEvent.setNgoAccount(oldEvent.getNgoAccount());

        return null;
    }
}

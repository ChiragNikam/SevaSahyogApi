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
    public Event updateEvent(Event updateEvent) {

        Optional<Event> oldEventOpt = eventsRepo.findById((long) updateEvent.getEventId());
        Event existingEvent = null;
        if (oldEventOpt.isPresent()){
            existingEvent = oldEventOpt.get();

            if (updateEvent.getName() != null) existingEvent.setName(updateEvent.getName());
            if (updateEvent.getShortDesc() != null) existingEvent.setShortDesc(updateEvent.getShortDesc());
            if (updateEvent.getLongDesc() != null) existingEvent.setLongDesc(updateEvent.getLongDesc());
            if (updateEvent.getOrganizer() != null) existingEvent.setOrganizer(updateEvent.getOrganizer());
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
        if (eventToDeleteOpt.isPresent()){
            eventsRepo.delete(eventToDeleteOpt.get());
        } else {
            throw new IllegalArgumentException("Event Not Found");
        }
    }
}

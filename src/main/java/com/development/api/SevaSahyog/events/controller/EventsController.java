package com.development.api.SevaSahyog.events.controller;

import com.development.api.SevaSahyog.events.data.Event;
import com.development.api.SevaSahyog.events.dto.CreateEventRequest;
import com.development.api.SevaSahyog.events.dto.CreateEventResponse;
import com.development.api.SevaSahyog.events.service.eventimpl.EventsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ngo/events")
public class EventsController {

    private final EventsServiceImpl eventsService;

    @GetMapping("/all") // to get all events
    public ResponseEntity<?> getAllEvents(){
        List<Event> allEvents = null;
        try {
            allEvents = eventsService.getAllEvents();
        } catch (Exception e){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getClass() + ": " + e.getLocalizedMessage());
        }

        return ResponseEntity.ok(allEvents);
    }

    @GetMapping(path = "/user/{id}") // to get a single event under a user
    public ResponseEntity<?> getEventsByUserId(@PathVariable String id) {
        try {
            List<Event> events = eventsService.getEventByUserId(id);
            if (events.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No events found for userId: " + id);
            }
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getClass() + ": " + e.getLocalizedMessage());
        }
    }

    @GetMapping(path = "/user/{userId}/{eventId}")
    public ResponseEntity<?> getEventByItsId(@PathVariable String userId, @PathVariable long eventId){
        try{
            Event eventToSearch = eventsService.getEventByItsId(eventId);
            return ResponseEntity.ok(eventToSearch);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getClass() + ": " + e.getLocalizedMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody CreateEventRequest event){
        Event savedEvent = null;
        try {
            savedEvent = eventsService.saveEvent(event);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getClass() + ": " + e.getLocalizedMessage());
        }

        if (savedEvent == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Event could not be saved");
        }

        BuildResponse response = new BuildResponse();

        CreateEventResponse eventResponse = response.buildCreateEvent(savedEvent);

        return ResponseEntity.ok(eventResponse);
    }

    @PutMapping
    public ResponseEntity<?> updateEvent(@RequestBody Event event){
        Event updatedEvent = null;
        try {
            updatedEvent = eventsService.updateEvent(event);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getClass() + ": " + e.getLocalizedMessage());
        }

        return ResponseEntity.ok(updatedEvent);
    }

    @PutMapping(path = "{eventId}/eventImages")
    public ResponseEntity<?> updateEventImagesUrls(@PathVariable Integer eventId, @RequestBody List<String> eventImages) {
        // get event of which images are to be updated
        Event eventToUpdate = eventsService.getEventByItsId(eventId);
        eventToUpdate.setEventImagesUrls(eventImages);
        Event updatedEvent;
        try {
            updatedEvent = eventsService.updateEvent(eventToUpdate);    // finally update event after setting image list to it
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getClass() + ": " + e.getLocalizedMessage());
        }

        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable long id){
        try {
            eventsService.deleteEvent(id);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getLocalizedMessage());
        }

        return ResponseEntity.ok("Event Deleted Successfully");
    }

    @GetMapping(path = "/user/{id}/upcoming")
    public ResponseEntity<?> getUpcomingEvents(@PathVariable String id) {
        try{
            List<Event> upcomingEvents = eventsService.filterUpcomingEvents(id);
            return ResponseEntity.ok(upcomingEvents);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }

    @GetMapping(path = "/user/{id}/pastEventYears")
    public ResponseEntity<?> getEventYears(@PathVariable String id) {
        try {
            Set<Integer> eventYears = eventsService.getEventYears(id);
            return ResponseEntity.ok(eventYears);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }
}
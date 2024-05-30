package com.development.api.SevaSahyog.events.repo;

import com.development.api.SevaSahyog.events.data.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface EventsRepo extends JpaRepository<Event, Long> {
    List<Event> findByNgoAccountUserId(String userId);
}

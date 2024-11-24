package com.development.api.SevaSahyog.events.data;

import com.development.api.SevaSahyog.auth.data.NgoAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int eventId;
    @Column(nullable = false)
    private String name;
    private String shortDesc;
    @Column(length = 1000)
    private String longDesc;
    @Column(nullable = false)
    private String organizer;
    private String organizerPhone;
    private String location;
    private int dd;
    private int mm;
    private int yyyy;
    @Column(nullable = false)
    private int status; // from 0:Past, 1:Ongoing, 2:Upcoming
    private List<String> eventImagesUrls;
    @ManyToOne
    private NgoAccount ngoAccount;
}


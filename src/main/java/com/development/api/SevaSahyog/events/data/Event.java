package com.development.api.SevaSahyog.events.data;

import com.development.api.SevaSahyog.auth.data.NgoAccount;
import jakarta.persistence.*;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int eventId;
    @Column(nullable = false)
    private String name;
    private String shortDesc;
    private String longDesc;
    @Column(nullable = false)
    private String organizer;
    private String location;
    private int dd;
    private int mm;
    private int yyyy;
    @Column(nullable = false)
    private int status; // from 0:Past, 1:Ongoing, 2:Upcoming
    @ManyToOne
    private NgoAccount ngoAccount;

    public NgoAccount getNgoAccount() {
        return ngoAccount;
    }

    public void setNgoAccount(NgoAccount ngoAccount) {
        this.ngoAccount = ngoAccount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDd() {
        return dd;
    }

    public void setDd(int dd) {
        this.dd = dd;
    }

    public int getMm() {
        return mm;
    }

    public void setMm(int mm) {
        this.mm = mm;
    }

    public int getYyyy() {
        return yyyy;
    }

    public void setYyyy(int yyyy) {
        this.yyyy = yyyy;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }
}


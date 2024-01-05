package com.radnoti.meetwave.Model;

import java.sql.Timestamp;
import java.util.Date;

public class createEventClass {

    String eventTitle;

    String description;

    java.util.Date dateOfTheEvent;

    String place;

    String founder;

    Timestamp dateOfCreatingEvent;

    int maxParticipants;

    String tickets;

    java.util.Date endOfEvent;

    public createEventClass(String eventTitle, String description, Date dateOfTheEvent, String place, String founder, Timestamp dateOfCreatingEvent, int maxParticipants, String tickets, Date endOfEvent) {
        this.eventTitle = eventTitle;
        this.description = description;
        this.dateOfTheEvent = dateOfTheEvent;
        this.place = place;
        this.founder = founder;
        this.dateOfCreatingEvent = dateOfCreatingEvent;
        this.maxParticipants = maxParticipants;
        this.tickets = tickets;
        this.endOfEvent = endOfEvent;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOfTheEvent() {
        return dateOfTheEvent;
    }

    public void setDateOfTheEvent(Date dateOfTheEvent) {
        this.dateOfTheEvent = dateOfTheEvent;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public Timestamp getDateOfCreatingEvent() {
        return dateOfCreatingEvent;
    }

    public void setDateOfCreatingEvent(Timestamp dateOfCreatingEvent) {
        this.dateOfCreatingEvent = dateOfCreatingEvent;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }

    public Date getEndOfEvent() {
        return endOfEvent;
    }

    public void setEndOfEvent(Date endOfEvent) {
        this.endOfEvent = endOfEvent;
    }
}

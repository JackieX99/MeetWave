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

    String ticketLink;

    java.util.Date endOfEvent;

   String address;

   String typeOfEvent;

   int countInterested;

   int countWillBeThere;

    public createEventClass(String eventTitle, String description, Date dateOfTheEvent, String place, String founder, Timestamp dateOfCreatingEvent, int maxParticipants, String ticketLink, Date endOfEvent, String address, String typeOfEvent, int countInterested, int countWillBeThere) {
        this.eventTitle = eventTitle;
        this.description = description;
        this.dateOfTheEvent = dateOfTheEvent;
        this.place = place;
        this.founder = founder;
        this.dateOfCreatingEvent = dateOfCreatingEvent;
        this.maxParticipants = maxParticipants;
        this.ticketLink = ticketLink;
        this.endOfEvent = endOfEvent;
        this.address = address;
        this.typeOfEvent = typeOfEvent;
        this.countInterested = countInterested;
        this.countWillBeThere = countWillBeThere;
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

    public String getTicketLink() {
        return ticketLink;
    }

    public void setTicketLink(String ticketLink) {
        this.ticketLink = ticketLink;
    }

    public Date getEndOfEvent() {
        return endOfEvent;
    }

    public void setEndOfEvent(Date endOfEvent) {
        this.endOfEvent = endOfEvent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTypeOfEvent() {
        return typeOfEvent;
    }

    public void setTypeOfEvent(String typeOfEvent) {
        this.typeOfEvent = typeOfEvent;
    }

    public int getCountInterested() {
        return countInterested;
    }

    public void setCountInterested(int countInterested) {
        this.countInterested = countInterested;
    }

    public int getCountWillBeThere() {
        return countWillBeThere;
    }

    public void setCountWillBeThere(int countWillBeThere) {
        this.countWillBeThere = countWillBeThere;
    }
}

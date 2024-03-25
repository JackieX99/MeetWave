package com.radnoti.meetwave.Model;

import java.util.Date;

public class updateEventClass {

    int eventID;

    String eventTitleIN;

    String descriptionIN;

    java.util.Date dateOfTheEventIN;

    String placeOfTheEventIN;

    String founderOfTheEventIN;

    int maxParticipantsIN;

    String ticketsLinkIN;

    java.util.Date endOfEventIN;

    String addressIN;

    Boolean typeOfEventIN;

    int countInterestedIN;

    int countWillBeThereIN;

    public updateEventClass(int eventID, String eventTitleIN, String descriptionIN, Date dateOfTheEventIN, String placeOfTheEventIN, String founderOfTheEventIN, int maxParticipantsIN, String ticketsLinkIN, Date endOfEventIN, String addressIN, Boolean typeOfEventIN, int countInterestedIN, int countWillBeThereIN) {
        this.eventID = eventID;
        this.eventTitleIN = eventTitleIN;
        this.descriptionIN = descriptionIN;
        this.dateOfTheEventIN = dateOfTheEventIN;
        this.placeOfTheEventIN = placeOfTheEventIN;
        this.founderOfTheEventIN = founderOfTheEventIN;
        this.maxParticipantsIN = maxParticipantsIN;
        this.ticketsLinkIN = ticketsLinkIN;
        this.endOfEventIN = endOfEventIN;
        this.addressIN = addressIN;
        this.typeOfEventIN = typeOfEventIN;
        this.countInterestedIN = countInterestedIN;
        this.countWillBeThereIN = countWillBeThereIN;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventTitleIN() {
        return eventTitleIN;
    }

    public void setEventTitleIN(String eventTitleIN) {
        this.eventTitleIN = eventTitleIN;
    }

    public String getDescriptionIN() {
        return descriptionIN;
    }

    public void setDescriptionIN(String descriptionIN) {
        this.descriptionIN = descriptionIN;
    }

    public Date getDateOfTheEventIN() {
        return dateOfTheEventIN;
    }

    public void setDateOfTheEventIN(Date dateOfTheEventIN) {
        this.dateOfTheEventIN = dateOfTheEventIN;
    }

    public String getPlaceOfTheEventIN() {
        return placeOfTheEventIN;
    }

    public void setPlaceOfTheEventIN(String placeOfTheEventIN) {
        this.placeOfTheEventIN = placeOfTheEventIN;
    }

    public String getFounderOfTheEventIN() {
        return founderOfTheEventIN;
    }

    public void setFounderOfTheEventIN(String founderOfTheEventIN) {
        this.founderOfTheEventIN = founderOfTheEventIN;
    }

    public int getMaxParticipantsIN() {
        return maxParticipantsIN;
    }

    public void setMaxParticipantsIN(int maxParticipantsIN) {
        this.maxParticipantsIN = maxParticipantsIN;
    }

    public String getTicketsLinkIN() {
        return ticketsLinkIN;
    }

    public void setTicketsLinkIN(String ticketsLinkIN) {
        this.ticketsLinkIN = ticketsLinkIN;
    }

    public Date getEndOfEventIN() {
        return endOfEventIN;
    }

    public void setEndOfEventIN(Date endOfEventIN) {
        this.endOfEventIN = endOfEventIN;
    }

    public String getAddressIN() {
        return addressIN;
    }

    public void setAddressIN(String addressIN) {
        this.addressIN = addressIN;
    }

    public Boolean getTypeOfEventIN() {
        return typeOfEventIN;
    }

    public void setTypeOfEventIN(Boolean typeOfEventIN) {
        this.typeOfEventIN = typeOfEventIN;
    }

    public int getCountInterestedIN() {
        return countInterestedIN;
    }

    public void setCountInterestedIN(int countInterestedIN) {
        this.countInterestedIN = countInterestedIN;
    }

    public int getCountWillBeThereIN() {
        return countWillBeThereIN;
    }

    public void setCountWillBeThereIN(int countWillBeThereIN) {
        this.countWillBeThereIN = countWillBeThereIN;
    }
}

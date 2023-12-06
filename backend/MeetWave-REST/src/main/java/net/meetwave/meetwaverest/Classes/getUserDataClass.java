package net.meetwave.meetwaverest.Classes;

import java.sql.Blob;
import java.util.Date;

public class getUserDataClass {

    String emailIN;

    int userID;

    String fullName;

    String email;

    String password;

    int subscriptionType;

    java.util.Date subscriptionEndOfDate;

    boolean isAdmin;

    boolean isBanned;

    boolean isMuted;

    Blob profilePicture;

    String phoneNumber;

    java.util.Date dateOfRegister;

    public getUserDataClass(String emailIN, int userID, String fullName, String email, String password, int subscriptionType, Date subscriptionEndOfDate, boolean isAdmin, boolean isBanned, boolean isMuted, Blob profilePicture, String phoneNumber, Date dateOfRegister) {
        this.emailIN = emailIN;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.subscriptionType = subscriptionType;
        this.subscriptionEndOfDate = subscriptionEndOfDate;
        this.isAdmin = isAdmin;
        this.isBanned = isBanned;
        this.isMuted = isMuted;
        this.profilePicture = profilePicture;
        this.phoneNumber = phoneNumber;
        this.dateOfRegister = dateOfRegister;
    }

    public getUserDataClass() {

    }

    public getUserDataClass(int userID, String fullName, String email, String password, int subscriptionType, Date subscriptionEndOfDate, boolean isAdmin, boolean isBanned, boolean isMuted, Blob profilePicture, String phoneNumber, Date dateOfRegister) {
    }

    public String getEmailIN() {
        return emailIN;
    }

    public void setEmailIN(String emailIN) {
        this.emailIN = emailIN;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(int subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Date getSubscriptionEndOfDate() {
        return subscriptionEndOfDate;
    }

    public void setSubscriptionEndOfDate(Date subscriptionEndOfDate) {
        this.subscriptionEndOfDate = subscriptionEndOfDate;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void setMuted(boolean muted) {
        isMuted = muted;
    }

    public Blob getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Blob profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfRegister() {
        return dateOfRegister;
    }

    public void setDateOfRegister(Date dateOfRegister) {
        this.dateOfRegister = dateOfRegister;
    }
}

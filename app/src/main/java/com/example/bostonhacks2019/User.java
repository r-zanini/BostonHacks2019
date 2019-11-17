package com.example.bostonhacks2019;

public class User {
    private String firstName;
    private String lastName;
    private String speakerAddress;

    public User(String firstName, String lastName, String speakerAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.speakerAddress = speakerAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpeakerAddress() {
        return speakerAddress;
    }

    public void setSpeakerAddress(String speakerAddress) {
        this.speakerAddress = speakerAddress;
    }
}

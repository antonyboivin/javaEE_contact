package com.example.model;


public class ContactForm {

    private String firstName;
    private String lastName;
    private long id;


    public String getFirstName() { return this.firstName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() { return this.lastName; }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

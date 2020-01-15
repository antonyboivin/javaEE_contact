package com.example.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class ListContacts {
    private List<Contact> contacts;

    public ListContacts() {
    }

    public ListContacts(Iterable<Contact> contacts) {
        this.contacts = new ArrayList<>();
        for (Contact c : contacts)
            this.contacts.add(c);
    }

    @XmlElement
    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}

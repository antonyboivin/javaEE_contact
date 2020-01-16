package com.example.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@Entity
@XmlRootElement(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    @ManyToMany
    @JoinTable(name = "CONTACT_ADDRESSE")
    private Collection<Adresse> adresses ;



    public Contact() {}

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
    @XmlElement
    public Long getId() {
        return id;
    }

    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    @XmlElement
    public String getLastName() {
        return lastName;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
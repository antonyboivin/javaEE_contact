package com.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Email {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String email;

    protected Email() {}

    public Email(String email) {
        this.email = email;
    }

    @ManyToOne
    @JoinColumn
    @NotNull
    private Contact contact;

    @Override
    public String toString() {
        return String.format(
                "Contact[id=%d, email='%s']",
                id, email);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}

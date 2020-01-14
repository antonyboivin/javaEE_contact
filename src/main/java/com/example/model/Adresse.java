package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Adresse {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String adresse;
    private Integer codePostal;
    private String ville;

    protected Adresse() {}

    public Adresse(String adresse,Integer codePostal , String ville) {
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    @Override
    public String toString() {
        return String.format(
                "Contact[id=%d, adresse='%s', codePostal='%s', ville='%s']",
                id, adresse, codePostal, ville);
    }

    public Long getId() {
        return id;
    }

    public String getAdresse() {
        return adresse;
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public String getVille() {
        return ville;
    }
}


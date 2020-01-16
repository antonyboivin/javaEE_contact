package com.example.model;


public class ContactForm {

    private String firstName;
    private String lastName;
    private long id;
    private String adresse;
    private Integer codepostal;
    private String ville;


    public String getFirstName() { return this.firstName; }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() { return this.lastName; }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getAdresse() { return this.adresse; }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getCodePostal() { return this.codepostal; }
    public void setCodepostal(Integer codepostal) {
        this.codepostal = codepostal;
    }

    public String getVille() { return this.ville; }
    public void setVille(String ville) {
        this.ville = ville;
    }
}

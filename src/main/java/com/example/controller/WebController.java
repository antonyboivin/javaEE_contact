package com.example.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.example.ModelApplication;
import com.example.model.*;
import jdk.jfr.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;


@Controller
public class WebController implements WebMvcConfigurer {
    private static final Logger log = LoggerFactory.getLogger(ModelApplication.class);
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    AdresseRepository adresseRepository;

    // Page d'accueil
    @GetMapping("/")
    public String accueil() {
        return "hello";
    }

    // Affichage des contacts
    @ModelAttribute("allContact")
    public Iterable<Contact> getContacts() {
        return contactRepository.findAll();
    }

    @GetMapping("/allContact")
    public String allContact(Contact contact) {
        return "allContact";
    }

    // Ajouter un contact via un formulaire
    @GetMapping("/addContact")
    public String showForm(ContactForm contactForm) {
        return "addContact";
    }

    @PostMapping("/addContact")
    public String checkContactInfo(@Valid ContactForm contactForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        Contact c = new Contact();
        c.setFirstName(contactForm.getFirstName());
        c.setId(contactForm.getId());
        c.setLastName(contactForm.getLastName());
        contactRepository.save(c);

        return "redirect:/allContact";
    }

    // Ajouter une adresse via un formulaire
    @ModelAttribute("allAdresse")
    public Iterable<Adresse> getAdresse() {
        return adresseRepository.findAll();
    }

    @GetMapping("/allAdresse")
    public String allAdresse(Adresse adresse) {
        return "allAdresse";
    }

    @GetMapping("/addAdresse")
    public String showFormAdresse(ContactForm contactForm) {
        return "addAdresse";
    }

    @PostMapping("/addAdresse")
    public String checkContactAdresseInfo(@Valid ContactForm contactForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        Adresse a = new Adresse();
        a.setId(contactForm.getId());
        a.setAdresse(contactForm.getAdresse());
        a.setCodePostal(contactForm.getCodePostal());
        a.setVille(contactForm.getVille());
        adresseRepository.save(a);

        return "redirect:/allContact";
    }

    //Editer un contact
    @RequestMapping("/updateContact/{id}")
    public String updateForm(ContactForm contactForm, @PathVariable long id) {
        Contact contact = contactRepository.findById(id);
        contactForm.setId(contact.getId());
        contactForm.setFirstName(contact.getFirstName());
        contactForm.setLastName(contact.getLastName());
        return "addContact";
    }

    //Supprimer un contact
    @RequestMapping("/delContact/{id}")
    public String delForm(@PathVariable long id) {
        Contact contact = contactRepository.findById(id);
        contactRepository.delete(contact);
        return "redirect:/allContact";
    }

    // Xml tous les contacts
    @GetMapping(value = "/xml", produces = {MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Object xml(@RequestParam String action, @RequestParam(defaultValue = "-1") long id) {
        switch (action)
        {
            case "listContacts":
                return allContactXml();
            case "getContact":
                return getContactById(id);
            case "delContact":
                return delContactById(id);
        }
        return null;
    }

    public ListContacts allContactXml() {
        return new ListContacts(contactRepository.findAll());
    }


    public Contact getContactById(@PathVariable long id) {
        return contactRepository.findById(id);
    }


    public ListContacts delContactById(@PathVariable long id) {
        Contact contactToDel = contactRepository.findById(id);
        contactRepository.delete(contactToDel);
        return new ListContacts(contactRepository.findAll());
    }
}


package com.example.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.example.ModelApplication;
import com.example.model.Contact;
import com.example.model.ContactForm;
import com.example.model.ContactRepository;
import com.example.model.ListContacts;
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


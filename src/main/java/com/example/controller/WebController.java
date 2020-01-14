package com.example.controller;

import javax.validation.Valid;

import com.example.ModelApplication;
import com.example.model.Contact;
import com.example.model.ContactForm;
import com.example.model.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;


@Controller
public class WebController implements WebMvcConfigurer {
    private static final Logger log = LoggerFactory.getLogger(ModelApplication.class);
    @Autowired
    ContactRepository contactRepository;

    // Affichage des contacts
    @ModelAttribute("allContact")
    public Iterable<Contact> getContacts(){
        return contactRepository.findAll();
    }

    @GetMapping("/allContact")
    public String allContact(Contact contact)
    {
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

        log.info("form.name="+ contactForm.getFirstName());
        log.info("form.age="+ contactForm.getLastName());

        return "redirect:/results";
    }
}

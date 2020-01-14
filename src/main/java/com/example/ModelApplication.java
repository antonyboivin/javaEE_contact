package com.example;

import com.example.model.Contact;
import com.example.model.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ModelApplication {

    private static final Logger log = LoggerFactory.getLogger(ModelApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ModelApplication.class);
    }

    @Bean
    public CommandLineRunner demo(ContactRepository repository) {
        return (args) -> {
            // save a few contacts
            repository.save(new Contact("Jack", "Bauer"));
            repository.save(new Contact("Chloe", "O'Brian"));
            repository.save(new Contact("Kim", "Bauer"));
            repository.save(new Contact("David", "Palmer"));
            repository.save(new Contact("Michelle", "Dessler"));

            // fetch all contact
            log.info("Contact found with findAll():");
            log.info("-------------------------------");
            for (Contact contact : repository.findAll()) {
                log.info(contact.toString());
            }
            log.info("");

            // fetch an individual contact by ID
            Contact contact = repository.findById(1L);
            log.info("Contact found with findById(1L):");
            log.info("--------------------------------");
            log.info(contact.toString());
            log.info("");

            // fetch contact by last name
            log.info("Contacts found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            // for (Contact bauer : repository.findByLastName("Bauer")) {
            //  log.info(bauer.toString());
            // }
            log.info("");
        };
    }

}
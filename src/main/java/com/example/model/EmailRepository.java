package com.example.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmailRepository extends CrudRepository<Email, Long> {

    List<Email> findByemail(String email);

}

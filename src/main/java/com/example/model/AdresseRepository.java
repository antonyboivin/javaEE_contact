package com.example.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdresseRepository extends CrudRepository<Adresse, Long> {
    List<Adresse> findByAdresse(String adresses);
}



package br.com.fmatheus.app.model.service;

import br.com.fmatheus.app.model.entity.Person;

import java.util.Optional;

public interface PersonService extends GenericService<Person, Integer> {

    Optional<Person> findByDocument(String document);
}

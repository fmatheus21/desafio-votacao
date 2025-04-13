package br.com.fmatheus.app.model.service.impl;

import br.com.fmatheus.app.controller.util.CharacterUtil;
import br.com.fmatheus.app.model.entity.Person;
import br.com.fmatheus.app.model.repository.PersonRepository;
import br.com.fmatheus.app.model.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    @Override
    public Collection<Person> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Person> findById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public Person save(Person person) {
        return this.repository.save(person);
    }

    @Override
    public void deleteById(Integer id) {
        this.repository.deleteById(id);
    }

    @Override
    public Optional<Person> findByDocument(String document) {
        return this.repository.findByDocument(CharacterUtil.removeSpecialCharacters(document));
    }
}

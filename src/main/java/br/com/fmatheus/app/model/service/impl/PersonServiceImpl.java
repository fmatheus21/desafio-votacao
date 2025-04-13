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
public non-sealed class PersonServiceImpl extends PersonHelper implements PersonService {

    private final PersonRepository repository;

    @Override
    public Collection<Person> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Person> findById(Integer id) {
        var result = this.repository.findById(id);
        return result.map(this::output);
    }

    @Override
    public Person save(Person person) {
        var input = input(person);
        return output(this.repository.save(input));
    }

    @Override
    public void deleteById(Integer id) {
        this.repository.deleteById(id);
    }

    @Override
    public Optional<Person> findByDocument(String document) {
        var result = this.repository.findByDocument(CharacterUtil.removeSpecialCharacters(document));
        return result.map(this::output);
    }
}

package br.com.fmatheus.app.model.service.impl;

import br.com.fmatheus.app.model.entity.Person;

import static br.com.fmatheus.app.controller.util.CharacterUtil.*;

public abstract sealed class PersonHelper permits PersonServiceImpl {

    protected Person input(Person person) {
        person.setName(convertAllUppercaseCharacters(person.getName()));
        person.setDocument(removeSpecialCharacters(person.getDocument()));
        return person;
    }

    protected Person output(Person person) {
        person.setName(convertFirstUppercaseCharacter(person.getName()));
        person.setDocument(removeSpecialCharacters(person.getDocument()));
        return person;
    }
}

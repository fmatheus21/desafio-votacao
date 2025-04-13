package br.com.fmatheus.app.model.repository;

import br.com.fmatheus.app.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByDocument(String document);
}

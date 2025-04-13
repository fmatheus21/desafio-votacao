package br.com.fmatheus.app.controller.converter;

import br.com.fmatheus.app.controller.dto.request.AssociatedRequest;
import br.com.fmatheus.app.controller.dto.response.AssociatedResponse;
import br.com.fmatheus.app.model.entity.Associated;
import br.com.fmatheus.app.model.entity.Person;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AssociatedConverter {

    public Person converterToEntity(AssociatedRequest request) {
        var person = Person.builder()
                .name(request.name())
                .document(request.document())
                .build();

        var asssocieted = Associated.builder()
                .person(person)
                .createdAt(LocalDateTime.now())
                .build();

        person.setAssociated(asssocieted);

        return person;

    }

    public AssociatedResponse converterToResponse(Person person) {
        return new AssociatedResponse(
                person.getName(),
                person.getDocument(),
                person.getAssociated().getCreatedAt()
        );
    }

}

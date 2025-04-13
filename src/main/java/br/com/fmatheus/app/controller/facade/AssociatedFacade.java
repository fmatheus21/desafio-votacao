package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.converter.AssociatedConverter;
import br.com.fmatheus.app.controller.dto.request.AssociatedRequest;
import br.com.fmatheus.app.controller.dto.response.AssociatedResponse;
import br.com.fmatheus.app.model.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AssociatedFacade {

    private final PersonService personService;
    private final AssociatedConverter associatedConverter;
    private final MessagesFacade messagesFacade;

    public AssociatedResponse create(AssociatedRequest request) {
        this.checksIfDocumentExists(request.document());

        var entity = this.associatedConverter.converterToEntity(request);
        var commit = this.personService.save(entity);
        return this.associatedConverter.converterToResponse(commit);

    }

    private void checksIfDocumentExists(String document) {
        var person = this.personService.findByDocument(document);
        if (person.isPresent()) {
            this.messagesFacade.errorDocumentAlready();
        }
    }
}

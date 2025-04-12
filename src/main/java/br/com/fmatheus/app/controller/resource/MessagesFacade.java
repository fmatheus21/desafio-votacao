package br.com.fmatheus.app.controller.resource;

import br.com.fmatheus.app.controller.exception.BadRequestException;
import br.com.fmatheus.app.controller.exception.NotFoundException;
import org.springframework.stereotype.Component;


@Component
public class MessagesFacade {

    private static final String EXIST_DOCUMENT = "message.error.exist-document";
    private static final String NOT_FOUND = "message.error.not-found";


    public void errorDocumentAlready() {
        throw new BadRequestException(EXIST_DOCUMENT);
    }

    public void errorNotFoundException() {
        throw new NotFoundException(NOT_FOUND);
    }


}

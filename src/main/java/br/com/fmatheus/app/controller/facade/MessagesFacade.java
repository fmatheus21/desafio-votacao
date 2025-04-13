package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.exception.BadRequestException;
import br.com.fmatheus.app.controller.exception.NotFoundException;
import org.springframework.stereotype.Component;


@Component
public class MessagesFacade {

    private static final String EXIST_DOCUMENT = "message.error.exist-document";
    private static final String NOT_FOUND = "message.error.not-found";
    private static final String TOPIC_NOT_FOUND = "message.error.topic-not-found";
    private static final String TOPIC_EXIST_SESSION = "message.error.topic-exist-session";
    private static final String SESSION_CLOSE = "message.error.session-closed";
    private static final String ASSOCIETED_NOT_FOUND = "message.error.associeted-not-found";
    private static final String SESSION_NOT_FOUND = "message.error.session-not-found";


    public BadRequestException errorDocumentAlready() {
        throw new BadRequestException(EXIST_DOCUMENT);
    }

    public NotFoundException errorNotFoundException() {
        throw new NotFoundException(NOT_FOUND);
    }

    public NotFoundException errorTopicNotFoundException() {
        throw new NotFoundException(TOPIC_NOT_FOUND);
    }

    public NotFoundException errorAsssocietedNotFoundException() {
        throw new NotFoundException(ASSOCIETED_NOT_FOUND);
    }

    public NotFoundException errorSessionNotFoundException() {
        throw new NotFoundException(SESSION_NOT_FOUND);
    }

    public void errorTopicAlreadySession() {
        throw new BadRequestException(TOPIC_EXIST_SESSION);
    }

    public void errorSessionIsClosed() {
        throw new BadRequestException(SESSION_CLOSE);
    }


}

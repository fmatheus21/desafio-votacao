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
    private static final String SESSION_CLOSE_CLOSED_VOTE_COUTING = "message.error.session-closed-vote-couting";
    private static final String ASSOCIATED_NOT_FOUND = "message.error.associated-not-found";
    private static final String SESSION_NOT_FOUND = "message.error.session-not-found";
    private static final String ASSOCIATED_ALREADY_VOTED = "message.error.associated-already-voted";


    public BadRequestException errorDocumentAlready() {
        return new BadRequestException(EXIST_DOCUMENT);
    }

    public NotFoundException errorNotFoundException() {
        return new NotFoundException(NOT_FOUND);
    }

    public NotFoundException errorTopicNotFoundException() {
        return new NotFoundException(TOPIC_NOT_FOUND);
    }

    public NotFoundException errorAsssocietedNotFoundException() {
        return new NotFoundException(ASSOCIATED_NOT_FOUND);
    }

    public NotFoundException errorSessionNotFoundException() {
        return new NotFoundException(SESSION_NOT_FOUND);
    }

    public BadRequestException errorTopicAlreadySession() {
        return new BadRequestException(TOPIC_EXIST_SESSION);
    }

    public BadRequestException errorSessionIsClosed() {
        return new BadRequestException(SESSION_CLOSE);
    }

    public BadRequestException errorSessionIsClosedForVoteCounting() {
        return new BadRequestException(SESSION_CLOSE_CLOSED_VOTE_COUTING);
    }

    public BadRequestException errorAssociatedAlreadyVoted() {
        return new BadRequestException(ASSOCIATED_ALREADY_VOTED);
    }


}

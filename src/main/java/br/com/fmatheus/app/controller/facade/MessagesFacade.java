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


    public void errorDocumentAlready() {
        throw new BadRequestException(EXIST_DOCUMENT);
    }

    public NotFoundException errorNotFoundException() {
        throw new NotFoundException(NOT_FOUND);
    }

    public NotFoundException errorTopicNotFoundException() {
        throw new NotFoundException(TOPIC_NOT_FOUND);
    }

    public NotFoundException errorAsssocietedNotFoundException() {
        throw new NotFoundException(ASSOCIATED_NOT_FOUND);
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

    public void errorSessionIsClosedForVoteCounting() {
        throw new BadRequestException(SESSION_CLOSE_CLOSED_VOTE_COUTING);
    }


    public void errorAssociatedAlreadyVoted() {
        throw new BadRequestException(ASSOCIATED_ALREADY_VOTED);
    }


}

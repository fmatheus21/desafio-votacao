package br.com.fmatheus.app.model.service;

import br.com.fmatheus.app.model.entity.Session;
import br.com.fmatheus.app.model.entity.Topic;

import java.util.Optional;
import java.util.UUID;

public interface SessionService extends GenericService<Session, UUID> {

    Optional<Session> findByTopic(Topic topic);
}

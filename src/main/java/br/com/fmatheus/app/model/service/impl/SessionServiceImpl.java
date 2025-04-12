package br.com.fmatheus.app.model.service.impl;

import br.com.fmatheus.app.model.entity.Session;
import br.com.fmatheus.app.model.entity.Topic;
import br.com.fmatheus.app.model.repository.SessionRepository;
import br.com.fmatheus.app.model.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository repository;

    @Override
    public Collection<Session> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Session> findById(UUID uuid) {
        return this.repository.findById(uuid);
    }

    @Override
    public Session save(Session session) {
        return this.repository.save(session);
    }

    @Override
    public void deleteById(UUID uuid) {
        this.repository.deleteById(uuid);
    }

    @Override
    public Optional<Session> findByTopic(Topic topic) {
        return this.repository.findByTopic(topic);
    }

    @Override
    public boolean isCurrentTimeWithinSession(UUID id) {
        return this.repository.isCurrentTimeWithinSession(id);
    }

}

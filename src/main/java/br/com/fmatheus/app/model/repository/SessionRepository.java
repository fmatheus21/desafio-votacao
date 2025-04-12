package br.com.fmatheus.app.model.repository;

import br.com.fmatheus.app.model.entity.Session;
import br.com.fmatheus.app.model.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {

    Optional<Session> findByTopic(Topic topic);
}

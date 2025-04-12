package br.com.fmatheus.app.model.repository;

import br.com.fmatheus.app.model.entity.Session;
import br.com.fmatheus.app.model.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {

    Optional<Session> findByTopic(Topic topic);

    @Query("""
                SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END
                FROM Session s
                WHERE s.id = :id
                AND CURRENT_TIMESTAMP BETWEEN s.start AND s.end
                AND s.open = true
            """)
    boolean isCurrentTimeWithinSession(@Param("id") UUID id);

}

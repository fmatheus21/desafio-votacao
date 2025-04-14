package br.com.fmatheus.app.controller.converter;

import br.com.fmatheus.app.config.properties.VotingProperties;
import br.com.fmatheus.app.controller.dto.request.SessionRequest;
import br.com.fmatheus.app.controller.dto.response.SessionResponse;
import br.com.fmatheus.app.model.entity.Session;
import br.com.fmatheus.app.model.entity.Topic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Component
public class SessionConverter {

    private final VotingProperties votingProperties;

    public Session converterToEntity(SessionRequest request, Topic topic) {
        var date = LocalDateTime.now();
        return Session.builder()
                .topic(topic)
                .start(date)
                .end(date.plusMinutes(votingProperties.getSession().getExpirationTime()))
                .build();
    }

    public SessionResponse converterToResponse(Session session) {
        return new SessionResponse(
                session.getId(),
                session.getStart(),
                session.getEnd(),
                new SessionResponse.TopicDto(
                        session.getTopic().getId(),
                        session.getTopic().getTitle(),
                        session.getTopic().getDescription()
                ));
    }
}

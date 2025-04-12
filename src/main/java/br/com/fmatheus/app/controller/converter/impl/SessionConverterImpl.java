package br.com.fmatheus.app.controller.converter.impl;

import br.com.fmatheus.app.config.properties.VotingProperties;
import br.com.fmatheus.app.controller.converter.SessionConverter;
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
public class SessionConverterImpl implements SessionConverter {

    private final VotingProperties votingProperties;

    @Override
    public Session converterToEntity(SessionRequest request) {
        var date = LocalDateTime.now();
        return Session.builder()
                .topic(Topic.builder().id(request.idTopic()).build())
                .start(date)
                .end(date.plusMinutes(votingProperties.getSession().getExpirationTime()))
                .open(true)
                .build();
    }

    @Override
    public SessionResponse converterToResponse(Session session) {
        return new SessionResponse(
                session.getId(),
                session.getStart(),
                session.getEnd(),
                session.isOpen(),
                new SessionResponse.TopicDto(
                        session.getTopic().getId(),
                        session.getTopic().getTitle(),
                        session.getTopic().getDescription()
                ));
    }
}

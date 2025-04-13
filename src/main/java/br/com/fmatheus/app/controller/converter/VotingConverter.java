package br.com.fmatheus.app.controller.converter;

import br.com.fmatheus.app.controller.dto.request.VotingRequest;
import br.com.fmatheus.app.controller.dto.response.VotingResponse;
import br.com.fmatheus.app.model.entity.Associated;
import br.com.fmatheus.app.model.entity.Session;
import br.com.fmatheus.app.model.entity.Voting;
import br.com.fmatheus.app.model.entity.VotingPk;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class VotingConverter {

    public Voting converterToEntity(VotingRequest request, Associated associated, Session session) {
        return Voting.builder()
                .pk(VotingPk.builder()
                        .associated(associated)
                        .session(session)
                        .build())
                .vote(request.vote())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public VotingResponse converterToResponse(Voting voting) {
        var associated = new VotingResponse.AssociatedDto(
                voting.getPk().getAssociated().getId(),
                voting.getPk().getAssociated().getPerson().getName()
        );

        var topic = new VotingResponse.TopicDto(
                voting.getPk().getSession().getTopic().getId(),
                voting.getPk().getSession().getTopic().getTitle(),
                voting.getPk().getSession().getTopic().getDescription(),
                voting.getPk().getSession().getTopic().getCreatedAt()
        );

        var session = new VotingResponse.SessionDto(
                voting.getPk().getSession().getId(),
                voting.getPk().getSession().getStart(),
                voting.getPk().getSession().getEnd(),
                voting.getPk().getSession().isOpen()
        );

        return new VotingResponse(associated, session, topic);

    }
}

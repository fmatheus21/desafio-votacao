package br.com.fmatheus.app.controller.converter.impl;

import br.com.fmatheus.app.controller.converter.VotingConverter;
import br.com.fmatheus.app.controller.dto.request.VotingRequest;
import br.com.fmatheus.app.controller.dto.response.VotingResponse;
import br.com.fmatheus.app.model.entity.Associeted;
import br.com.fmatheus.app.model.entity.Session;
import br.com.fmatheus.app.model.entity.Voting;
import br.com.fmatheus.app.model.entity.VotingPk;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class VotingConverterImpl implements VotingConverter {

    @Override
    public Voting converterToEntity(VotingRequest request) {
        return Voting.builder()
                .pk(VotingPk.builder()
                        .associeted(Associeted.builder()
                                .id(request.idAssocieted())
                                .build())
                        .session(Session.builder()
                                .id(request.idSession())
                                .build())
                        .build())
                .vote(request.vote())
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Override
    public VotingResponse converterToResponse(Voting voting) {
        return new VotingResponse(
                voting.getPk().getAssocieted().getPerson().getName(),
                voting.getPk().getSession().getTopic().getId(),
                voting.getPk().getSession().getTopic().getTitle(),
                voting.getPk().getSession().getId());
    }
}

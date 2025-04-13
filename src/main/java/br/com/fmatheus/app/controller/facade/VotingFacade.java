package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.converter.VotingConverter;
import br.com.fmatheus.app.controller.dto.request.VotingRequest;
import br.com.fmatheus.app.controller.dto.response.VotingResponse;
import br.com.fmatheus.app.model.entity.Associeted;
import br.com.fmatheus.app.model.entity.Session;
import br.com.fmatheus.app.model.service.AssocietedService;
import br.com.fmatheus.app.model.service.SessionService;
import br.com.fmatheus.app.model.service.VotingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class VotingFacade {

    private final VotingService votingService;
    private final SessionService sessionService;
    private final AssocietedService associetedService;
    private final VotingConverter votingConverter;
    private final MessagesFacade messagesFacade;

    public VotingResponse create(VotingRequest request) {
        this.validatesWhetherSessionIsOpen(request.idSession());
        var associeted = this.checksIfAssociatedExists(request.idAssocieted());
        var session = this.checksIfSessionExists(request.idSession());

        var entity = this.votingConverter.converterToEntity(request, associeted, session);
        var commit = this.votingService.save(entity);
        return this.votingConverter.converterToResponse(commit);

    }

    private void validatesWhetherSessionIsOpen(UUID idSessio) {
        var isOpen = this.sessionService.isCurrentTimeWithinSession(idSessio);
        if (!isOpen) {
            this.messagesFacade.errorSessionIsClosed();
        }
    }

    private Associeted checksIfAssociatedExists(UUID idAssocieted) {
        return this.associetedService.findById(idAssocieted).orElseThrow(this.messagesFacade::errorAsssocietedNotFoundException);
    }

    private Session checksIfSessionExists(UUID idAssocieted) {
        return this.sessionService.findById(idAssocieted).orElseThrow(this.messagesFacade::errorSessionNotFoundException);
    }


}

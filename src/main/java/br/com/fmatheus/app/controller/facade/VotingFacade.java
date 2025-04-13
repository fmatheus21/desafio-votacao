package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.converter.VotingConverter;
import br.com.fmatheus.app.controller.dto.request.VotingRequest;
import br.com.fmatheus.app.controller.dto.response.VotingResponse;
import br.com.fmatheus.app.model.entity.Associated;
import br.com.fmatheus.app.model.entity.Session;
import br.com.fmatheus.app.model.entity.VotingPk;
import br.com.fmatheus.app.model.service.AssociatedService;
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
    private final AssociatedService associatedService;
    private final VotingConverter votingConverter;
    private final MessagesFacade messagesFacade;

    public VotingResponse create(VotingRequest request) {
        this.validatesWhetherSessionIsOpen(request.idSession());
        var associated = this.checksIfAssociatedExists(request.idAssociated());
        var session = this.checksIfSessionExists(request.idSession());

        this.checksIfAssociatedAlreadyVoted(associated, session);

        var entity = this.votingConverter.converterToEntity(request, associated, session);
        var commit = this.votingService.save(entity);
        return this.votingConverter.converterToResponse(commit);

    }

    private void validatesWhetherSessionIsOpen(UUID id) {
        log.info("Verificando se a sessão com ID {} está aberto para votação.", id);
        var isOpen = this.sessionService.isCurrentTimeWithinSession(id);
        if (!isOpen) {
            this.messagesFacade.errorSessionIsClosed();
        }
    }

    private Associated checksIfAssociatedExists(UUID id) {
        log.info("Verificando se o asssociado com ID {} existe.", id);
        return this.associatedService.findById(id).orElseThrow(this.messagesFacade::errorAsssocietedNotFoundException);
    }

    private Session checksIfSessionExists(UUID id) {
        log.info("Verificando se a sessão com ID {} existe.", id);
        return this.sessionService.findById(id).orElseThrow(this.messagesFacade::errorSessionNotFoundException);
    }

    private void checksIfAssociatedAlreadyVoted(Associated associated, Session session) {
        var pk = VotingPk.builder()
                .associated(associated)
                .session(session)
                .build();

        var result = this.votingService.findById(pk);

        if (result.isPresent()) {
            this.messagesFacade.errorAssociatedAlreadyVoted();
        }
    }


}

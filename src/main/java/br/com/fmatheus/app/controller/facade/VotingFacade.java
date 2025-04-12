package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.dto.request.VotingRequest;
import br.com.fmatheus.app.controller.dto.response.VotingResponse;
import br.com.fmatheus.app.model.service.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class VotingFacade {

    private final SessionService sessionService;
    private final MessagesFacade messagesFacade;

    public VotingResponse create(VotingRequest request) {
        this.validatesWhetherSessionIsOpen(request.idSession());
        return null;
    }

    private void validatesWhetherSessionIsOpen(UUID idSessio) {
        var isOpen = this.sessionService.isCurrentTimeWithinSession(idSessio);
        if (!isOpen) {
            this.messagesFacade.errorSessionIsClosed();
        }
    }


}

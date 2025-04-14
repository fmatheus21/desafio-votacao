package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.converter.VotingConverter;
import br.com.fmatheus.app.controller.dto.request.VotingRequest;
import br.com.fmatheus.app.controller.dto.response.VotingResponse;
import br.com.fmatheus.app.controller.enums.VoteEnum;
import br.com.fmatheus.app.model.entity.Associated;
import br.com.fmatheus.app.model.entity.Session;
import br.com.fmatheus.app.model.entity.VotingPk;
import br.com.fmatheus.app.model.service.AssociatedService;
import br.com.fmatheus.app.model.service.SessionService;
import br.com.fmatheus.app.model.service.VotingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Fachada responsável por orquestrar a lógica de negócio relacionada à votação,
 * incluindo validações, criação de votos e contagem de votos por sessão.
 *
 * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class VotingFacade {

    private final VotingService votingService;
    private final SessionService sessionService;
    private final AssociatedService associatedService;
    private final VotingConverter votingConverter;
    private final MessagesFacade messagesFacade;


    /**
     * Cria um novo voto após realizar as validações necessárias.
     *
     * @param request Dados da requisição de votação.
     * @return {@link VotingResponse} representando o voto persistido.
     * @throws br.com.fmatheus.app.controller.exception.BadRequestException em caso de falha de validação.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    public VotingResponse create(VotingRequest request) {

        var associated = this.checksIfAssociatedExists(request.idAssociated());
        var session = this.checksIfSessionExists(request.idSession());

        this.validatesWhetherSessionIsOpen(request.idSession());
        this.checksIfAssociatedAlreadyVoted(associated, session);

        var entity = this.votingConverter.converterToEntity(request, associated, session);
        var commit = this.votingService.save(entity);
        return this.votingConverter.converterToResponse(commit);

    }

    /**
     * Retorna a contagem de votos "Sim" e "Não" de uma sessão, caso ela esteja encerrada.
     *
     * @param idSession ID da sessão.
     * @return Mapa com o texto do voto como chave ("Sim", "Não") e a contagem como valor.
     * @throws br.com.fmatheus.app.controller.exception.BadRequestException caso a sessão ainda esteja aberta.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    public Map<String, Long> countVotes(UUID idSession) {

        this.validateSessionIsClosedForVoteCounting(idSession);

        Map<String, Long> map = new HashMap<>();
        var votes = this.votingService.countVotesBySessionId(idSession);
        Long yesVotes = votes.get(VoteEnum.YES);
        Long noVotes = votes.get(VoteEnum.NO);

        map.put(VoteEnum.YES.getValue(), yesVotes);
        map.put(VoteEnum.NO.getValue(), noVotes);

        return map;

    }

    /**
     * Verifica se a sessão está encerrada para permitir a contagem de votos.
     *
     * @param idSession ID da sessão.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    private void validateSessionIsClosedForVoteCounting(UUID idSession) {
        log.info("Verificando se a sessão com ID {} está fechada para contabilizar os votos.", idSession);
        var isOpen = this.sessionService.isCurrentTimeWithinSession(idSession);
        if (isOpen) {
            this.messagesFacade.errorSessionIsClosedForVoteCounting();
        }
    }

    /**
     * Verifica se a sessão está aberta para permitir a votação.
     *
     * @param idSession ID da sessão.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    private void validatesWhetherSessionIsOpen(UUID idSession) {
        log.info("Verificando se a sessão com ID {} está aberto para votação.", idSession);
        var isOpen = this.sessionService.isCurrentTimeWithinSession(idSession);
        if (!isOpen) {
            this.messagesFacade.errorSessionIsClosed();
        }
    }

    /**
     * Verifica se o associado com o ID fornecido existe.
     *
     * @param id ID do associado.
     * @return {@link Associated} encontrado.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    private Associated checksIfAssociatedExists(UUID id) {
        log.info("Verificando se o asssociado com ID {} existe.", id);
        return this.associatedService.findById(id).orElseThrow(this.messagesFacade::errorAsssocietedNotFoundException);
    }

    /**
     * Verifica se a sessão com o ID fornecido existe.
     *
     * @param id ID da sessão.
     * @return {@link Session} encontrada.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    private Session checksIfSessionExists(UUID id) {
        log.info("Verificando se a sessão com ID {} existe.", id);
        return this.sessionService.findById(id).orElseThrow(this.messagesFacade::errorSessionNotFoundException);
    }

    /**
     * Verifica se o associado já votou na sessão informada.
     *
     * @param associated Associado.
     * @param session    Sessão.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
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

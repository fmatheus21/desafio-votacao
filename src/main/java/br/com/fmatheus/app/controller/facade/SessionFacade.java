package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.converter.SessionConverter;
import br.com.fmatheus.app.controller.dto.request.SessionRequest;
import br.com.fmatheus.app.controller.dto.response.SessionResponse;
import br.com.fmatheus.app.model.entity.Topic;
import br.com.fmatheus.app.model.service.SessionService;
import br.com.fmatheus.app.model.service.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;


/**
 * Facade responsável por gerenciar a criação de sessões de votação para uma determinada pauta.
 * <p>
 * Este componente coordena as etapas de verificação da pauta, validação de existência de sessão e persistência.
 * </p>
 *
 * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SessionFacade {

    private final SessionService sessionService;
    private final TopicService topicService;
    private final SessionConverter sessionConverter;
    private final MessagesFacade messagesFacade;

    /**
     * Cria uma nova sessão de votação para a pauta informada.
     * <p>
     * Antes da criação, o método garante que a pauta existe e que ainda não há uma sessão cadastrada para ela.
     * </p>
     *
     * @param request objeto {@link SessionRequest} contendo as informações necessárias para criar a sessão.
     * @return {@link SessionResponse} contendo os dados da sessão criada.
     * @throws RuntimeException caso a pauta não exista ou já possua uma sessão vinculada.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    public SessionResponse create(SessionRequest request) {

        var topic = this.checksIfTopicExists(request.idTopic());
        this.validateSession(request.idTopic());

        var entity = this.sessionConverter.converterToEntity(request, topic);
        var commit = this.sessionService.save(entity);
        return this.sessionConverter.converterToResponse(commit);
    }

    /**
     * Verifica se uma pauta com o ID informado existe no banco de dados.
     *
     * @param id identificador da pauta.
     * @return entidade {@link Topic} correspondente.
     * @throws RuntimeException caso a pauta não seja encontrada.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    private Topic checksIfTopicExists(UUID id) {
        log.info("Buscando pela Pauta com ID {}", id);
        return this.topicService.findById(id).orElseThrow(this.messagesFacade::errorTopicNotFoundException);
    }

    /**
     * Verifica se já existe uma sessão vinculada à pauta informada.
     * <p>
     * Caso já exista, uma exceção de negócio é lançada.
     * </p>
     *
     * @param idTopic identificador da pauta.
     * @throws RuntimeException caso a pauta já possua uma sessão registrada.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    private void validateSession(UUID idTopic) {
        log.info("Verificando se a pauta com ID {} já existe na Sessão.", idTopic);
        var session = this.sessionService.findByTopic(Topic.builder().id(idTopic).build());
        if (session.isPresent()) {
            this.messagesFacade.errorTopicAlreadySession();
        }
    }

}

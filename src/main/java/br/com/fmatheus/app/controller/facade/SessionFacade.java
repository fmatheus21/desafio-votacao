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

@Slf4j
@RequiredArgsConstructor
@Component
public class SessionFacade {

    private final SessionService sessionService;
    private final TopicService topicService;
    private final SessionConverter sessionConverter;
    private final MessagesFacade messagesFacade;

    public SessionResponse create(SessionRequest request) {

        this.validateTopic(request.idTopic());
        this.validateSession(request.idTopic());

        var entity = this.sessionConverter.converterToEntity(request);
        var commit = this.sessionService.save(entity);
        return this.sessionConverter.converterToResponse(commit);
    }

    private void validateTopic(UUID id) {
        log.info("Buscando pela Pauta com ID {}", id);
        this.topicService.findById(id).orElseThrow(this.messagesFacade::errorTopicNotFoundException);
    }

    private void validateSession(UUID idTopic) {
        log.info("Verificando se a pauta com ID {} já existe na Sessão.", idTopic);
        var session = this.sessionService.findByTopic(Topic.builder().id(idTopic).build());
        if (session.isPresent()) {
            this.messagesFacade.errorTopicAlreadySession();
        }
    }
}

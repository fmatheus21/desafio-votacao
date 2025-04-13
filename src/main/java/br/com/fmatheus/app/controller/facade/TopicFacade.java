package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.converter.TopicConverter;
import br.com.fmatheus.app.controller.dto.request.TopicRequest;
import br.com.fmatheus.app.controller.dto.response.TopicResponse;
import br.com.fmatheus.app.model.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * Facade responsável pelo fluxo de criação de pautas.
 * <p>
 * Realiza a conversão da requisição para entidade, delega a persistência ao serviço e retorna a resposta formatada.
 * </p>
 *
 * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
 */
@RequiredArgsConstructor
@Component
public class TopicFacade {

    private final TopicService topicService;
    private final TopicConverter topicConverter;

    /**
     * Cria uma nova pauta a partir dos dados informados na requisição.
     *
     * @param request objeto {@link TopicRequest} contendo os dados da pauta a ser criada.
     * @return {@link TopicResponse} com os dados da pauta persistida.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    public TopicResponse create(TopicRequest request) {
        var entity = this.topicConverter.converterToEntity(request);
        var commit = this.topicService.save(entity);
        return this.topicConverter.converterToResponse(commit);
    }
}

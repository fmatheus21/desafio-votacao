package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.controller.converter.TopicConverter;
import br.com.fmatheus.app.controller.dto.request.TopicRequest;
import br.com.fmatheus.app.controller.dto.response.TopicResponse;
import br.com.fmatheus.app.model.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TopicFacade {

    private final TopicService topicService;
    private final TopicConverter topicConverter;

    public TopicResponse create(TopicRequest request) {
        var entity = this.topicConverter.converterToEntity(request);
        var commit = this.topicService.save(entity);
        return this.topicConverter.converterToResponse(commit);
    }
}

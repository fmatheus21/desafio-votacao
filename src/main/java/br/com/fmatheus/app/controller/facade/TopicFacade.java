package br.com.fmatheus.app.controller.facade;

import br.com.fmatheus.app.model.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TopicFacade {

    private final TopicService topicService;
}

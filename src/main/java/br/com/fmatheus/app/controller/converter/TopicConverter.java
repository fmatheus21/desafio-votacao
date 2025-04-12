package br.com.fmatheus.app.controller.converter;

import br.com.fmatheus.app.controller.dto.request.TopicRequest;
import br.com.fmatheus.app.controller.dto.response.TopicResponse;
import br.com.fmatheus.app.model.entity.Topic;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TopicConverter {

    public Topic converterToEntity(TopicRequest request) {
        return Topic.builder()
                .title(request.title())
                .description(request.description())
                .createdAt(LocalDateTime.now())
                .open(false)
                .build();
    }

    public TopicResponse converterToResponse(Topic topic) {
        return new TopicResponse(
                topic.getId(),
                topic.getTitle(),
                topic.getDescription(),
                topic.getCreatedAt(),
                topic.isOpen());
    }

}

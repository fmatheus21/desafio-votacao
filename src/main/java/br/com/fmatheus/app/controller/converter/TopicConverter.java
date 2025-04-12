package br.com.fmatheus.app.controller.converter;

import br.com.fmatheus.app.controller.dto.request.TopicRequest;
import br.com.fmatheus.app.controller.dto.response.TopicResponse;
import br.com.fmatheus.app.model.entity.Topic;

public interface TopicConverter extends MapperConverter<Topic, TopicRequest, TopicResponse> {
}

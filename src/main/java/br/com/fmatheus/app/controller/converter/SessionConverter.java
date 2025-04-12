package br.com.fmatheus.app.controller.converter;

import br.com.fmatheus.app.controller.dto.request.SessionRequest;
import br.com.fmatheus.app.controller.dto.response.SessionResponse;
import br.com.fmatheus.app.model.entity.Session;

public interface SessionConverter extends MapperConverter<Session, SessionRequest, SessionResponse> {
}

package br.com.fmatheus.app.controller.converter;

import br.com.fmatheus.app.controller.dto.request.VotingRequest;
import br.com.fmatheus.app.controller.dto.response.VotingResponse;
import br.com.fmatheus.app.model.entity.Voting;

public interface VotingConverter extends MapperConverter<Voting, VotingRequest, VotingResponse> {
}

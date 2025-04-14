package br.com.fmatheus.app.model.repository.query;

import br.com.fmatheus.app.controller.enums.VoteEnum;

import java.util.Map;
import java.util.UUID;

public interface VotingRepositoryQuery {

    Map<VoteEnum, Long> countVotesBySessionId(UUID idSession);
}

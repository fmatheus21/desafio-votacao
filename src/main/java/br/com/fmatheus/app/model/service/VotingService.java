package br.com.fmatheus.app.model.service;

import br.com.fmatheus.app.controller.enums.VoteEnum;
import br.com.fmatheus.app.model.entity.Voting;
import br.com.fmatheus.app.model.entity.VotingPk;

import java.util.Map;
import java.util.UUID;

public interface VotingService extends GenericService<Voting, VotingPk> {

    Map<VoteEnum, Long> countVotesBySessionId(UUID sessionId);
}

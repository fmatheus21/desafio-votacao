package br.com.fmatheus.app.model.service.impl;

import br.com.fmatheus.app.controller.enums.VoteEnum;
import br.com.fmatheus.app.model.entity.Voting;
import br.com.fmatheus.app.model.entity.VotingPk;
import br.com.fmatheus.app.model.repository.VotingRepository;
import br.com.fmatheus.app.model.service.VotingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VotingServiceImpl implements VotingService {

    private final VotingRepository repository;

    @Override
    public Collection<Voting> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Voting> findById(VotingPk pk) {
        return this.repository.findById(pk);
    }

    @Override
    public Voting save(Voting voting) {
        return this.repository.save(voting);
    }

    @Override
    public void deleteById(VotingPk pk) {
        this.repository.deleteById(pk);
    }

    @Override
    public Map<VoteEnum, Long> countVotesBySessionId(UUID idSession) {
        return this.repository.countVotesBySessionId(idSession);
    }
}

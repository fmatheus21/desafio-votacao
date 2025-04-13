package br.com.fmatheus.app.model.repository;

import br.com.fmatheus.app.model.entity.Voting;
import br.com.fmatheus.app.model.entity.VotingPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingRepository extends JpaRepository<Voting, VotingPk> {


}

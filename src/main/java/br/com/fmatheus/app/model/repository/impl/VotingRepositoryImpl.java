package br.com.fmatheus.app.model.repository.impl;

import br.com.fmatheus.app.controller.enums.VoteEnum;
import br.com.fmatheus.app.model.entity.Voting;
import br.com.fmatheus.app.model.repository.query.VotingRepositoryQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

public class VotingRepositoryImpl implements VotingRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Conta os votos por sessão, retornando a quantidade de votos "YES" e "NO".
     *
     * @param idSession ID da sessão.
     * @return Mapa contendo a contagem por tipo de voto.
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
    @Override
    public Map<VoteEnum, Long> countVotesBySessionId(UUID idSession) {
        var builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
        var root = criteriaQuery.from(Voting.class);

        Path<UUID> idSessionPath = root.get("pk").get("session").get("id");

        criteriaQuery.multiselect(root.get("vote"), builder.count(root))
                .where(builder.equal(idSessionPath, idSession))
                .groupBy(root.get("vote"));

        var resultList = this.manager.createQuery(criteriaQuery).getResultList();

        Map<VoteEnum, Long> resultMap = new EnumMap<>(VoteEnum.class);
        for (Object[] row : resultList) {
            VoteEnum vote = (VoteEnum) row[0];
            Long count = (Long) row[1];
            resultMap.put(vote, count);
        }

        for (VoteEnum type : VoteEnum.values()) {
            resultMap.putIfAbsent(type, 0L);
        }

        return resultMap;
    }
}

package br.com.fmatheus.app.model.repository.impl;

import br.com.fmatheus.app.model.entity.Associated;
import br.com.fmatheus.app.model.entity.Person;
import br.com.fmatheus.app.model.repository.filter.AssociatedFilter;
import br.com.fmatheus.app.model.repository.impl.restriction.AssociatedRestriction;
import br.com.fmatheus.app.model.repository.query.AssociatedRepositoryQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class AssociatedRepositoryImpl extends AssociatedRestriction implements AssociatedRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Associated> findAllFilter(Pageable pageable, AssociatedFilter filter) {
        var builder = manager.getCriteriaBuilder();
        var criteriaQuery = builder.createQuery(Associated.class);
        var root = criteriaQuery.from(Associated.class);
        Join<Person, Associated> joinPerson = root.join("person");
        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteriaQuery
                .where(predicates)
                .orderBy(builder.asc(joinPerson.get("name")));

        var typedQuery = manager.createQuery(criteriaQuery);

        addPageRestrictions(typedQuery, pageable);

        return new PageImpl<>(typedQuery.getResultList(), pageable, total(filter));
    }

    @Override
    public Long total(AssociatedFilter filter) {
        var builder = manager.getCriteriaBuilder();
        var criteriaQuery = builder.createQuery(Long.class);
        var root = criteriaQuery.from(Associated.class);

        Predicate[] predicates = createRestrictions(filter, builder, root);
        criteriaQuery.where(predicates);

        criteriaQuery.select(builder.count(root));

        return manager.createQuery(criteriaQuery).getSingleResult();
    }
}

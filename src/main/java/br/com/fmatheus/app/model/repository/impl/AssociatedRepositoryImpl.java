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

/**
 * Implementação customizada do repositório {@link br.com.fmatheus.app.model.repository.AssociatedRepository},
 * responsável por aplicar filtros personalizados usando {@link jakarta.persistence.criteria.CriteriaBuilder}
 * e {@link jakarta.persistence.criteria.CriteriaQuery} com paginação.
 * <p>
 * Estende {@link AssociatedRestriction} para reutilizar as restrições de filtro.
 * </p>
 *
 * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
 */
public class AssociatedRepositoryImpl extends AssociatedRestriction implements AssociatedRepositoryQuery {

    /**
     * Gerenciador de entidades usado para construir e executar queries dinâmicas.
     */
    @PersistenceContext
    private EntityManager manager;


    /**
     * Retorna uma página de entidades {@link Associated} de acordo com os filtros informados.
     *
     * @param pageable objeto contendo as informações de paginação
     * @param filter   filtros aplicados à consulta
     * @return página de {@link Associated} filtrados
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
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

    /**
     * Conta o total de registros de {@link Associated} que atendem aos filtros informados.
     *
     * @param filter filtros aplicados à consulta
     * @return quantidade total de registros que satisfazem os filtros
     * @author <a href="mailto:fernando.matheuss@hotmail.com">Fernando Matheus</a>
     */
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

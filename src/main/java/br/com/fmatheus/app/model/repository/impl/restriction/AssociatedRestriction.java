package br.com.fmatheus.app.model.repository.impl.restriction;

import br.com.fmatheus.app.controller.util.CharacterUtil;
import br.com.fmatheus.app.model.entity.Associated;
import br.com.fmatheus.app.model.entity.Person;
import br.com.fmatheus.app.model.repository.filter.AssociatedFilter;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AssociatedRestriction {

    private static final String PERCENT = "%";


    protected Predicate[] createRestrictions(AssociatedFilter filter, CriteriaBuilder builder, Root<Associated> root) {

        List<Predicate> predicates = new ArrayList<>();

        Join<Person, Associated> joinPerson = root.join("person");

        if (Objects.nonNull(filter.getName())) {
            predicates.add(builder.like(builder.lower(joinPerson.get("name")),
                    PERCENT + filter.getName().toLowerCase() + PERCENT));
        }

        if (Objects.nonNull(filter.getDocument())) {
            predicates.add(builder.like(builder.lower(joinPerson.get("document")),
                    PERCENT + CharacterUtil.removeSpecialCharacters(filter.getDocument()) + PERCENT));
        }

        return predicates.toArray(new Predicate[0]);

    }


    protected void addPageRestrictions(TypedQuery<Associated> typedQuery, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPageRecord = currentPage * totalRecordsPerPage;

        typedQuery.setFirstResult(firstPageRecord);
        typedQuery.setMaxResults(totalRecordsPerPage);
    }


}

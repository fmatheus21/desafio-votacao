package br.com.fmatheus.app.model.repository.query;

import br.com.fmatheus.app.model.entity.Associated;
import br.com.fmatheus.app.model.repository.filter.AssociatedFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AssociatedRepositoryQuery {

    Page<Associated> findAllFilter(Pageable pageable, AssociatedFilter filter);

    Long total(AssociatedFilter filter);
}

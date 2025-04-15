package br.com.fmatheus.app.model.service;

import br.com.fmatheus.app.model.entity.Associated;
import br.com.fmatheus.app.model.repository.filter.AssociatedFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AssociatedService extends GenericService<Associated, UUID> {

    Page<Associated> findAllFilter(Pageable pageable, AssociatedFilter filter);

    Long total(AssociatedFilter filter);

}

package br.com.fmatheus.app.model.service.impl;

import br.com.fmatheus.app.model.entity.Associated;
import br.com.fmatheus.app.model.repository.AssociatedRepository;
import br.com.fmatheus.app.model.repository.filter.AssociatedFilter;
import br.com.fmatheus.app.model.service.AssociatedService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AssociatedServiceImpl implements AssociatedService {

    private final AssociatedRepository repository;

    @Override
    public Collection<Associated> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Associated> findById(UUID uuid) {
        return this.repository.findById(uuid);
    }

    @Override
    public Associated save(Associated associated) {
        return this.repository.save(associated);
    }

    @Override
    public void deleteById(UUID uuid) {
        this.repository.deleteById(uuid);
    }

    @Override
    public Page<Associated> findAllFilter(Pageable pageable, AssociatedFilter filter) {
        return this.repository.findAllFilter(pageable, filter);
    }

    @Override
    public Long total(AssociatedFilter filter) {
        return this.repository.total(filter);
    }
}

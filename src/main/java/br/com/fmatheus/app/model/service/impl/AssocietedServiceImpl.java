package br.com.fmatheus.app.model.service.impl;

import br.com.fmatheus.app.model.entity.Associeted;
import br.com.fmatheus.app.model.repository.AssocietedRepository;
import br.com.fmatheus.app.model.service.AssocietedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AssocietedServiceImpl implements AssocietedService {

    private final AssocietedRepository repository;

    @Override
    public Collection<Associeted> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Associeted> findById(UUID uuid) {
        return this.repository.findById(uuid);
    }

    @Override
    public Associeted save(Associeted associeted) {
        return this.repository.save(associeted);
    }

    @Override
    public void deleteById(UUID uuid) {
        this.repository.deleteById(uuid);
    }

}

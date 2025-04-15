package br.com.fmatheus.app.model.repository;

import br.com.fmatheus.app.model.entity.Associated;
import br.com.fmatheus.app.model.repository.query.AssociatedRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssociatedRepository extends JpaRepository<Associated, UUID>, AssociatedRepositoryQuery {
}

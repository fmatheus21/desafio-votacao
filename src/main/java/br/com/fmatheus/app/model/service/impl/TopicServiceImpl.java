package br.com.fmatheus.app.model.service.impl;

import br.com.fmatheus.app.model.entity.Topic;
import br.com.fmatheus.app.model.repository.TopicRepository;
import br.com.fmatheus.app.model.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository repository;

    @Override
    public Collection<Topic> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Topic> findById(UUID uuid) {
        return this.repository.findById(uuid);
    }

    @Override
    public Topic save(Topic topic) {
        return this.repository.save(topic);
    }

    @Override
    public void deleteById(UUID uuid) {
        this.repository.deleteById(uuid);
    }
    
}

package br.com.fmatheus.app.controller.resource;

import br.com.fmatheus.app.controller.dto.request.TopicRequest;
import br.com.fmatheus.app.controller.dto.response.TopicResponse;
import br.com.fmatheus.app.controller.facade.TopicFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/topics")
public class TopicResource {

    private final TopicFacade facade;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TopicResponse create(@RequestBody @Valid TopicRequest request) {
        return this.facade.create(request);
    }
}

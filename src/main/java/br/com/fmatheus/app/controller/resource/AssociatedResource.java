package br.com.fmatheus.app.controller.resource;

import br.com.fmatheus.app.controller.dto.request.AssociatedRequest;
import br.com.fmatheus.app.controller.dto.response.AssociatedResponse;
import br.com.fmatheus.app.controller.facade.AssociatedFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/associates")
public class AssociatedResource {

    private final AssociatedFacade facade;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AssociatedResponse create(@RequestBody @Valid AssociatedRequest request) {
        return this.facade.create(request);
    }
}

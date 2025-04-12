package br.com.fmatheus.app.controller.resource;

import br.com.fmatheus.app.controller.dto.request.SessionRequest;
import br.com.fmatheus.app.controller.dto.response.SessionResponse;
import br.com.fmatheus.app.controller.facade.SessionFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sessions")
public class SessionResource {

    private final SessionFacade facade;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SessionResponse create(@RequestBody @Valid SessionRequest request) {
        return this.facade.create(request);
    }
}

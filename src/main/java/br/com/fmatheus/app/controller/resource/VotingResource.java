package br.com.fmatheus.app.controller.resource;

import br.com.fmatheus.app.controller.dto.request.VotingRequest;
import br.com.fmatheus.app.controller.dto.response.VotingResponse;
import br.com.fmatheus.app.controller.facade.VotingFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/votings")
public class VotingResource {

    private final VotingFacade facade;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public VotingResponse create(@RequestBody @Valid VotingRequest request) {
        return this.facade.create(request);
    }

    @Transactional(readOnly = true)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/session/{idSession}/count")
    public Map<String, Long> countVotes(@PathVariable UUID idSession) {
        return this.facade.countVotes(idSession);
    }
}

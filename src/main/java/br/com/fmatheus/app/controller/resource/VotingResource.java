package br.com.fmatheus.app.controller.resource;

import br.com.fmatheus.app.controller.dto.request.VotingRequest;
import br.com.fmatheus.app.controller.dto.response.TopicResponse;
import br.com.fmatheus.app.controller.dto.response.VotingResponse;
import br.com.fmatheus.app.controller.facade.VotingFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@Tag(name = "Voting", description = "Operações relacionadas à votação")
@RequiredArgsConstructor
@RestController
@RequestMapping("/votings")
public class VotingResource {

    private final VotingFacade facade;


    @Operation(
            summary = "Criação de uma votação",
            description = "Cria uma nova votação com base no request enviado.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Votação criada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopicResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dados necessários para criação da votação",
            required = true,
            content = @Content(schema = @Schema(implementation = VotingRequest.class)))
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public VotingResponse create(@RequestBody @Valid VotingRequest request) {
        return this.facade.create(request);
    }


    @Operation(
            summary = "Contagem de votos",
            description = "Retorna a contagem de votos de uma sessão de votação.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Contagem de votos retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))})
    @Transactional(readOnly = true)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/session/{idSession}/count")
    public Map<String, Long> countVotes(@PathVariable @Parameter(description = "ID da sessão") UUID idSession) {
        return this.facade.countVotes(idSession);
    }
}

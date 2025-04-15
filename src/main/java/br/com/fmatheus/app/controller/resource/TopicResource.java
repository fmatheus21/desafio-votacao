package br.com.fmatheus.app.controller.resource;

import br.com.fmatheus.app.controller.dto.request.TopicRequest;
import br.com.fmatheus.app.controller.dto.response.TopicResponse;
import br.com.fmatheus.app.controller.facade.TopicFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Topic", description = "Operações relacionadas à pauta")
@RequiredArgsConstructor
@RestController
@RequestMapping("/topics")
public class TopicResource {

    private final TopicFacade facade;

    @Operation(
            summary = "Criar pauta",
            description = "Cria uma nova pauta com título e descrição")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Pauta criada com sucesso",
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
            description = "Dados necessários para criação da pauta",
            required = true,
            content = @Content(schema = @Schema(implementation = TopicRequest.class)))
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TopicResponse create(@RequestBody @Valid TopicRequest request) {
        return this.facade.create(request);
    }
}

package br.com.fmatheus.app.controller.resource;

import br.com.fmatheus.app.controller.dto.request.SessionRequest;
import br.com.fmatheus.app.controller.dto.response.SessionResponse;
import br.com.fmatheus.app.controller.facade.SessionFacade;
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

@Tag(name = "Session", description = "Operações relacionadas à sessão")
@RequiredArgsConstructor
@RestController
@RequestMapping("/sessions")
public class SessionResource {

    private final SessionFacade facade;


    @Operation(
            summary = "Criar sessão de votação",
            description = "Cria uma nova sessão para votação com horário de início e fim.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Sessão criada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SessionResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dados necessários para criar a sessão",
            required = true,
            content = @Content(schema = @Schema(implementation = SessionRequest.class)))
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SessionResponse create(@RequestBody @Valid SessionRequest request) {
        return this.facade.create(request);
    }
}

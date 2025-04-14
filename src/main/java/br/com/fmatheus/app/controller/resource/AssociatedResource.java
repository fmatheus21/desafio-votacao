package br.com.fmatheus.app.controller.resource;

import br.com.fmatheus.app.controller.dto.request.AssociatedRequest;
import br.com.fmatheus.app.controller.dto.response.AssociatedResponse;
import br.com.fmatheus.app.controller.facade.AssociatedFacade;
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

@Tag(name = "Associated", description = "Operações relacionadas à associado")
@RequiredArgsConstructor
@RestController
@RequestMapping("/associates")
public class AssociatedResource {

    private final AssociatedFacade facade;

    @Operation(
            summary = "Criar associado",
            description = "Cria um novo associado com nome e documento")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Associado criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AssociatedResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dados necessários para criação do associado",
            required = true,
            content = @Content(schema = @Schema(implementation = AssociatedRequest.class)))
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AssociatedResponse create(@RequestBody @Valid AssociatedRequest request) {
        return this.facade.create(request);
    }
}

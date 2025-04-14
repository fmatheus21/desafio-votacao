package br.com.fmatheus.app.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Objeto de requisição para criar e abrir uma sessão para votação.")
public record SessionRequest(
        @Schema(description = "ID da pauta", example = "e3d8cf04-9eaa-45a9-9f78-81f2b9e5b77b")
        @NotNull UUID idTopic) {
}

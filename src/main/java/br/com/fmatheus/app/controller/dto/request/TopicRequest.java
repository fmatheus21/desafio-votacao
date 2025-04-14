package br.com.fmatheus.app.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Objeto de requisição para criar uma pauta")
public record TopicRequest(
        @Schema(description = "Título da pauta")
        @NotBlank @Size(min = 5, max = 100) String title,

        @Schema(description = "Descrição da pauta")
        @NotBlank @Size(min = 10) String description) {
}

package br.com.fmatheus.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Representação de uma pauta")
public record TopicResponse(
        @Schema(description = "ID da pauta", example = "d290f1ee-6c54-4b01-90e6-d701748f0851")
        UUID id,

        @Schema(description = "Título do tópico", example = "Alteração no regimento interno")
        String title,

        @Schema(description = "Descrição detalhada da pauta", example = "Proposta de alteração no artigo")
        String description,

        @Schema(description = "Data de criação da pauta", example = "12/04/2025 09:45:00", type = "string", pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime createdAt) {
}

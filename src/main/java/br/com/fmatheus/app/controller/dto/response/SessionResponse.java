package br.com.fmatheus.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Representação de uma sessão de votação")
public record SessionResponse(
        @Schema(description = "ID da sessão", example = "b3b10c4e-fd30-4a3e-a5d1-5b25a6e5f3d1")
        UUID id,

        @Schema(description = "Data e hora de início da sessão", example = "12/04/2025 10:00:00", type = "string", pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime start,

        @Schema(description = "Data e hora de término da sessão", example = "12/04/2025 11:00:00", type = "string", pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime end,

        @Schema(description = "Dados da pauta associado à sessão")
        TopicDto topic) {

    @Schema(description = "Representação de uma pauta de votação")
    public record TopicDto(
            @Schema(description = "ID da pauta", example = "f47ac10b-58cc-4372-a567-0e02b2c3d479")
            UUID id,

            @Schema(description = "Título da pauta", example = "Reforma do Estatuto do Idoso")
            String title,

            @Schema(description = "Descrição detalhada do tópico", example = "Discussão sobre a reforma do estatuto do idoso")
            String description) {

    }
}

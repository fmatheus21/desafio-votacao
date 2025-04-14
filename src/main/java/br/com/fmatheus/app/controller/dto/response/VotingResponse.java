package br.com.fmatheus.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Resposta com os dados do voto realizado")
public record VotingResponse(
        @Schema(description = "Dados do associado que votou")
        AssociatedDto associated,

        @Schema(description = "Dados da sessão de votação")
        SessionDto session,

        @Schema(description = "Dados da pauta da votação")
        TopicDto topic) {

    @Schema(description = "Informações do associado")
    public record AssociatedDto(
            @Schema(description = "ID do associado", example = "123e4567-e89b-12d3-a456-426614174000")
            UUID id,

            @Schema(description = "Nome do associado", example = "Pedro Nunes")
            String name) {

    }

    @Schema(description = "Informações da pauta")
    public record TopicDto(
            @Schema(description = "ID da pauta", example = "abc123e4-5f67-89ab-cdef-1234567890ab")
            UUID id,

            @Schema(description = "Título da pauta", example = "Mudança no Estatuto")
            String title,

            @Schema(description = "Descrição detalhada da pauta", example = "Discussão sobre as mudanças no artigo 1º")
            String description,

            @Schema(description = "Data de criação da pauta", example = "12/04/2025 14:30:00", type = "string", pattern = "dd/MM/yyyy HH:mm:ss")
            @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
            LocalDateTime createdAt) {
    }

    @Schema(description = "Informações da sessão de votação")
    public record SessionDto(
            @Schema(description = "ID da sessão", example = "def45678-1234-4b01-90e6-123456789abc")
            UUID id,

            @Schema(description = "Data e hora de início da sessão", example = "12/04/2025 09:00:00", type = "string", pattern = "dd/MM/yyyy HH:mm:ss")
            @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
            LocalDateTime start,

            @Schema(description = "Data e hora de fim da sessão", example = "12/04/2025 10:00:00", type = "string", pattern = "dd/MM/yyyy HH:mm:ss")
            @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
            LocalDateTime end) {
    }
}

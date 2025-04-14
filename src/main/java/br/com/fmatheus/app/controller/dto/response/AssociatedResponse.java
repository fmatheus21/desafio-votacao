package br.com.fmatheus.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Objeto de resposta da criação de um associado.")
public record AssociatedResponse(
        @Schema(description = "Identificador único do associado", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,

        @Schema(description = "Nome completo do associado", example = "João da Silva")
        String name,

        @Schema(description = "Documento do associado (CPF)", example = "12345678901")
        String document,

        @Schema(description = "Data de criação do associado", example = "12/04/2025 15:30:00", type = "string", pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime createdAt) {
}

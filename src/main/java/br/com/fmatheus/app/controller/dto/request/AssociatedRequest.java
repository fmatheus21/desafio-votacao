package br.com.fmatheus.app.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Requisição de associado")
public record AssociatedRequest(
        @Schema(description = "Nome do associado")
        @NotBlank @Size(min = 2, max = 70) String name,

        @Schema(description = "Número do documento (CPF)", example = "114.543.765-05")
        @NotBlank @Size(min = 11, max = 20) String document) {
}

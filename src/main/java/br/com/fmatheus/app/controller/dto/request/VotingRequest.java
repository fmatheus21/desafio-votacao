package br.com.fmatheus.app.controller.dto.request;

import br.com.fmatheus.app.controller.enums.VoteEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Representação de um associado")
public record VotingRequest(
        @Schema(description = "ID do associado", example = "e3d8cf04-9eaa-45a9-9f78-81f2b9e5b77b")
        @NotNull UUID idAssociated,

        @Schema(description = "ID da sessão", example = "b7f3c3fa-981d-4df7-9911-dfb3a2dbce8b")
        @NotNull UUID idSession,

        @Schema(description = "Voto do associado (YES ou NO)", example = "YES")
        @NotNull VoteEnum vote) {
}

package br.com.fmatheus.app.controller.dto.request;

import br.com.fmatheus.app.controller.enums.VoteEnum;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record VotingRequest(@NotNull UUID idAssociated,
                            @NotNull UUID idSession,
                            @NotNull VoteEnum vote) {
}

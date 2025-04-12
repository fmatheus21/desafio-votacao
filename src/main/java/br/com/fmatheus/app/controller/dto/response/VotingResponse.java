package br.com.fmatheus.app.controller.dto.response;

import java.util.UUID;

public record VotingResponse(String associatedName,
                             UUID idTopic,
                             String topicTitle,
                             UUID idSession) {
}

package br.com.fmatheus.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record VotingResponse(AssocietedDto associated,
                             SessionDto session,
                             TopicDto topic) {

    public record AssocietedDto(UUID id,
                                String name) {

    }

    public record TopicDto(UUID id,
                           String title,
                           String description,
                           @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime createdAt) {
    }

    public record SessionDto(UUID id,
                             @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime start,
                             @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime end,
                             boolean open) {

    }
}

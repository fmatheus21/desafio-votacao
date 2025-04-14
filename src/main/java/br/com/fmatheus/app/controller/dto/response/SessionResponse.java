package br.com.fmatheus.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record SessionResponse(UUID id,
                              @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime start,
                              @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime end,
                              TopicDto topic) {

    public record TopicDto(UUID id,
                           String title,
                           String description) {

    }
}

package br.com.fmatheus.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record AssociatedResponse(UUID id,
                                 String name,
                                 String document,
                                 @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime createdAt) {
}

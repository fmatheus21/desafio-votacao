package br.com.fmatheus.app.controller.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SessionRequest(@NotNull UUID idTopic) {
}

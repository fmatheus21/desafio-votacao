package br.com.fmatheus.app.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TopicRequest(@NotBlank @Size(min = 5, max = 30) String title,
                           @NotBlank @Size(min = 10) String description) {
}

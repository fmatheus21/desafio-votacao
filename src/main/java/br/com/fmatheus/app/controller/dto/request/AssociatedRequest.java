package br.com.fmatheus.app.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AssociatedRequest(@NotBlank @Size(min = 2, max = 70) String name,
                                @NotBlank @Size(min = 11, max = 20) String document) {
}

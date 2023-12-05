package br.com.qfa.resources.domain.user;

import jakarta.validation.constraints.NotNull;

public record RegisterDTO(@NotNull String login, @NotNull String password, @NotNull String email, @NotNull UserRole role) {
}
package br.com.qfa.resources.domain.user;

public record RegisterDTO(String login, String password, String email, UserRole role) {
}
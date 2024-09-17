package br.com.fiap.workmatch.users.dto;

import java.time.LocalDateTime;

import br.com.fiap.workmatch.users.User;

public record UserRequest(
        String name,
        LocalDateTime dateOfBirth,
        String genero,
        String PhoneNumber,
        String email,
        String password,
        String role
        
) {
    public User toModel() {
        return User.builder()
                .name(name)
                .dateOfBirth(dateOfBirth)
                .genero(genero)
                .phoneNumber(PhoneNumber)
                .email(email)
                .password(password)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .role(role)
                .build();
    }
}

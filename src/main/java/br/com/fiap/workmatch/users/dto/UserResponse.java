package br.com.fiap.workmatch.users.dto;


import java.time.LocalDateTime;

import br.com.fiap.workmatch.users.User;

public record UserResponse (
        Long id,
        String name,
        String genero,
        String PhoneNumber,
        String email,
        LocalDateTime createdAt
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getGenero(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
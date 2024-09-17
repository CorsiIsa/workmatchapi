package br.com.fiap.workmatch.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    LocalDateTime dateOfBirth;
    String genero;
    String phoneNumber;
    String email;
    String password;
    String avatar;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

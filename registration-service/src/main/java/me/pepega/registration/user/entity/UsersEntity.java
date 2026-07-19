package me.pepega.registration.user.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.Objects;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "cipher_phone_number", unique = true, length = 250)
    private String cipherPhoneNumber;

    @Column(name = "cipher_email", unique = true, length = 250)
    private String cipherEmail;

    @Column(name = "hash_password", nullable = false, columnDefinition = "TEXT")
    private String hashPassword;

    @Column(nullable = false, unique = true, length = 250)
    private String token;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider", nullable = false, length = 20)
    private AuthProvider authProvider = AuthProvider.LOCAL;
}
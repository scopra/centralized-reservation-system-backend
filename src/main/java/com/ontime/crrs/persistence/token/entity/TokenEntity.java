package com.ontime.crrs.persistence.token.entity;

import com.ontime.crrs.persistence.token.util.TokenType;
import com.ontime.crrs.persistence.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.ontime.crrs.persistence.token.util.TokenType.BEARER;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity {

    @Id
    @GeneratedValue
    @Column(
            name = "token_id",
            columnDefinition = "uuid"
    )
    private UUID id;

    @Column(
            name = "token",
            unique = true
    )
    public String token;

    @Enumerated(STRING)
    @Column(name = "token_type")
    public TokenType tokenType = BEARER;

    @Column(name = "revoked")
    public boolean revoked;

    @Column(name = "expired")
    public boolean expired;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    public UserEntity user;
}
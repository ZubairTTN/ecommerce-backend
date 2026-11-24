package com.zubair.ecommerce.entity.token;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlackListedTokens {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(columnDefinition = "TEXT")
    private String token;
    private Date expiryTime;

    public BlackListedTokens(String token, Date expiryTime) {
        this.token = token;
        this.expiryTime = expiryTime;
    }
}

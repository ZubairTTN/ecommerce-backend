package com.zubair.ecommerce.repository;

import com.zubair.ecommerce.entity.ActivationToken;
import com.zubair.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ActivationTokenRepository extends JpaRepository<ActivationToken, UUID> {

    Optional<ActivationToken> findByToken(String token);

    void deleteByUser(User user);
}

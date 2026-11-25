package com.zubair.ecommerce.repository;

import com.zubair.ecommerce.entity.Role;
import com.zubair.ecommerce.enums.RoleEnums;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByAuthority(RoleEnums authority);

    boolean existsByAuthority(RoleEnums authority);
}

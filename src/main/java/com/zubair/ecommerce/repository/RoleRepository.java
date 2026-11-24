package com.zubair.ecommerce.repository;

import com.zubair.ecommerce.entity.users.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {
    Optional<Role> findByAuthority(String authority);
}

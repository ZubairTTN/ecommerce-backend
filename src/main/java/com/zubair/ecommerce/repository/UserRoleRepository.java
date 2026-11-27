package com.zubair.ecommerce.repository;

import com.zubair.ecommerce.entity.UserRole;
import com.zubair.ecommerce.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}

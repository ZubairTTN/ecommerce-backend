package com.zubair.ecommerce.repository;

import com.zubair.ecommerce.entity.UserRole;
import com.zubair.ecommerce.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}

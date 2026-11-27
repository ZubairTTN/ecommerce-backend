package com.zubair.ecommerce.bootstrap;

import com.zubair.ecommerce.entity.Role;
import com.zubair.ecommerce.enums.RoleEnums;
import com.zubair.ecommerce.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/* CommandLineRunner -> execute a piece of code automatically during application startup. */

@Component
@RequiredArgsConstructor
public class AdminBootstrap implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        seedRoles();
    }

    private void seedRoles() {
        for (RoleEnums authority : RoleEnums.values()) {
            if (!roleRepository.existsByAuthority(authority)) {
                Role role = new Role();
                role.setAuthority(authority);
                roleRepository.save(role);
            }
        }
    }

}

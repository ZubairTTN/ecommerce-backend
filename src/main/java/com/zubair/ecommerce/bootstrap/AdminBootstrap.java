package com.zubair.ecommerce.bootstrap;

import com.zubair.ecommerce.entity.Role;
import com.zubair.ecommerce.entity.User;
import com.zubair.ecommerce.entity.UserRole;
import com.zubair.ecommerce.entity.UserRoleId;
import com.zubair.ecommerce.enums.RoleEnums;
import com.zubair.ecommerce.repository.RoleRepository;
import com.zubair.ecommerce.repository.UserRepository;
import com.zubair.ecommerce.repository.UserRoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

/* CommandLineRunner -> execute a piece of code automatically during application startup. */

@Component
@RequiredArgsConstructor
public class AdminBootstrap implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Override
    @Transactional
    public void run(String... args) {
        seedRoles();
        seedAdmin();
    }

    /* 	Create missing roles */
    private void seedRoles() {
        for (RoleEnums authority : RoleEnums.values()) {
            if (!roleRepository.existsByAuthority(authority)) {
                Role role = new Role();
                role.setAuthority(authority);
                roleRepository.save(role);
            }
        }
    }

    private void seedAdmin()
    {
        /* Retrieve ADMIN role */
        Role adminRole = roleRepository
                .findByAuthority(RoleEnums.ROLE_ADMIN)
                .orElseThrow(() ->
                        new IllegalStateException("Admin role is missing"));

        String email = adminEmail.trim().toLowerCase(Locale.ROOT);
        Optional<User> existingUser = userRepository.findByEmailIgnoreCase(email);

        if(existingUser.isPresent())
        {
            UserRoleId assignmentId = new UserRoleId(
                    existingUser.get().getId(),
                    adminRole.getId()
            );

            if(!userRoleRepository.existsById(assignmentId))
            {
                throw new IllegalStateException("Configured admin email belongs to a non-admin account");
            }

            return;
        }

        User admin = new User();
        admin.setEmail(email);
        admin.setFirstName("System");
        admin.setLastName("Admin");
        admin.setHashedPassword(passwordEncoder.encode(adminPassword));
        admin.setActive(true);
        admin.setLocked(false);
        admin.setExpired(false);
        admin.setDeleted(false);
        admin.setInvalidAttemptCount(0);
        admin.setPasswordUpdateDate(LocalDateTime.now());

        userRepository.save(admin);

        UserRole assignment = new UserRole();
        assignment.setId(new UserRoleId(admin.getId(), adminRole.getId()));
        assignment.setUser(admin);
        assignment.setRole(adminRole);

        userRoleRepository.save(assignment);

    }

}

package com.zubair.ecommerce.service.serviceImpl;


import com.zubair.ecommerce.entity.users.Role;
import com.zubair.ecommerce.enums.RoleType;
import com.zubair.ecommerce.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        for(RoleType roleType : RoleType.values())
        {
            String authority = roleType.getValue();

            if (roleRepository.findByAuthority(authority).isEmpty()) {
                Role role = new Role();
                role.setAuthority(authority);
                roleRepository.save(role);
            }
        }

    }
}

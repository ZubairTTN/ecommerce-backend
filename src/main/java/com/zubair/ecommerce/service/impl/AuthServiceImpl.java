package com.zubair.ecommerce.service.impl;
import com.zubair.ecommerce.repository.ActivationTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import com.zubair.ecommerce.dto.ApiResponseDTO;
import com.zubair.ecommerce.dto.RegisterRequestDTO;
import com.zubair.ecommerce.entity.*;
import com.zubair.ecommerce.enums.RoleEnums;
import com.zubair.ecommerce.exception.ApiException;
import com.zubair.ecommerce.repository.*;
import com.zubair.ecommerce.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;


@Service
@RequiredArgsConstructor /* creates constructor for fields with final keyword and @NonNull annotation */
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ActivationTokenRepository activationTokenRepository;

    @Value("${app.activation-token.validity-hours}")
    private long activationTokenValidityHours;

    @Override
    @Transactional
    public ApiResponseDTO register(RegisterRequestDTO request) {
        if(!request.getPassword().equals(request.getConfirmPassword()))
        {
            throw new ApiException("Password do not match", 400);
        }

        String email = request.getEmail()
                .trim()
                .toLowerCase(Locale.ROOT);

        if(userRepository.existsByEmailIgnoreCase(email))
        {
            throw new ApiException("Email is already registered", 409);
        }

        Role customerRole = roleRepository.findByAuthority(RoleEnums.ROLE_CUSTOMER).orElseThrow(() -> new IllegalStateException("Customer role is missing"));

        User user = new User();
        user.setEmail(email);
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());
        user.setHashedPassword(passwordEncoder.encode(request.getPassword()));
        user.setActive(false);
        user.setPasswordUpdateDate(LocalDateTime.now());
        userRepository.save(user);

        Customer customer = new Customer();
        customer.setUser(user);
        customer.setContact(request.getPhoneNumber());
        customerRepository.save(customer);

        UserRole assignment = new UserRole();
        assignment.setId(new UserRoleId(user.getId(), customerRole.getId()));
        assignment.setUser(user);
        assignment.setRole(customerRole);

        userRoleRepository.save(assignment);
        createActivationToken(user);

        return new ApiResponseDTO("Customer account created. Activation is required.", 201);

    }

    private void createActivationToken(User user) {
        ActivationToken activationToken = new ActivationToken();

        activationToken.setToken(UUID.randomUUID().toString());
        activationToken.setUser(user);
        activationToken.setExpiryDate(
                LocalDateTime.now().plusHours(activationTokenValidityHours)
        );

        activationTokenRepository.save(activationToken);
    }


}

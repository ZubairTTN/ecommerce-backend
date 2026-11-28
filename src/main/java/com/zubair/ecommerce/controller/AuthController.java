package com.zubair.ecommerce.controller;


import com.zubair.ecommerce.dto.ApiResponseDTO;
import com.zubair.ecommerce.dto.RegisterRequestDTO;
import com.zubair.ecommerce.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register/customer")
    public ResponseEntity<ApiResponseDTO> registerCustomer(@Valid @RequestBody RegisterRequestDTO request)
    {
        ApiResponseDTO response = authService.register(request);

        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }
}

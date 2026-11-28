package com.zubair.ecommerce.service;

import com.zubair.ecommerce.dto.ApiResponseDTO;
import com.zubair.ecommerce.dto.RegisterRequestDTO;

/* Input — RegisterRequestDTO:
* Output — ApiResponseDTO: */

public interface AuthService {

    ApiResponseDTO register(RegisterRequestDTO request);
}

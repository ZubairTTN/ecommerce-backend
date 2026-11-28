package com.zubair.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/* basic response structure */

@Getter
@AllArgsConstructor
public class ApiResponseDTO {
    private String message;
    private int status;
}

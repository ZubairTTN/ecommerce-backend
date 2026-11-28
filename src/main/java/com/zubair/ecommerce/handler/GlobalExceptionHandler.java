package com.zubair.ecommerce.handler;


import com.zubair.ecommerce.dto.ApiResponseDTO;
import com.zubair.ecommerce.exception.ApiException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice /* Provides centralized exception handling with response-body serialization. Serialization: Java Object → JSON, Deserialization: JSON → Java Object*/
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class) /* @ExceptionHandler tells spring whenever this particular type of exception occurs during request processing, use the following method to handle it. */
    public ResponseEntity<ApiResponseDTO> handleApiException(ApiException exception)
    {
        return ResponseEntity
                .status(exception.getStatus())
                .body(new ApiResponseDTO(
                        exception.getMessage(),
                        exception.getStatus()
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) /* This method handles validation failures that occur when the client provides invalid request data. RegisterRequestDTO failure */
    public ResponseEntity<ApiResponseDTO> handleValidationException(
            MethodArgumentNotValidException exception )
    {
        String message = exception.getBindingResult() /* It contains details about the errors that occurred while binding or validating the object. */
                .getFieldErrors() /* A FieldError is a Spring class representing an error associated with a specific field. */
                .stream()
                .map(error ->
                        error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity
                .badRequest()
                .body(new ApiResponseDTO(message, 400));
    }

    @ExceptionHandler(DataIntegrityViolationException.class) /* This method handles exceptions related to database integrity. */
    public ResponseEntity<ApiResponseDTO> handleDataConflict(DataIntegrityViolationException exception)
    {
        return ResponseEntity
                .status(409)
                .body(new ApiResponseDTO(
                        "The request conflicts with stored data",
                        409
                ));
    }
}

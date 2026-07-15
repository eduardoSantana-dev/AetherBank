package com.controller

import com.dtos.ErroResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(ex: RuntimeException): ResponseEntity<ErroResponse> {
        
        return ResponseEntity
            .unprocessableEntity() // HTTP 422
            .body(ErroResponse(ex.message ?: "Erro"))
    }
}
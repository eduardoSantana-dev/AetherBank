package com.dtos

data class RegistroRequest(
    val nome: String,
    val cpf: String,
    val email: String,
    val numero: String,
    val senha: String
)

data class LoginRequest(
    val email: String,
    val password: String,
)
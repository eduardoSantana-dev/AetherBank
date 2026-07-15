package com.dtos

import java.math.BigDecimal

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

data class TransferenciaRequest(
    val chavePix: String,
    val valor: BigDecimal,
)
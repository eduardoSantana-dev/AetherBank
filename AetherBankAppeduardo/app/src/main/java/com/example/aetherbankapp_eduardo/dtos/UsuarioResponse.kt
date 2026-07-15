package com.dtos

import java.math.BigDecimal
import java.time.LocalDateTime


data class CredenciaisResponse(
    val message: String?,
    val id : Long?,
    val nome: String?,
    val cpf: String?,
    val email: String?,
    val numero: String?,
)


data class LoginResponse(
    val message: String?,
    val token: String?,
)

data class ContaResponse(
    val saldo: String?
)


data class TransferenciaResponse(
    val concluida: Boolean,
    val mensagem: String,
    val valor: BigDecimal,
    val user: String,
    val data: String
)

data class ErroResponse(
    val mensagem: String
)

data class PagamentoResponse(
    val nomeFavorecido: String,
    val saldo: String?
)
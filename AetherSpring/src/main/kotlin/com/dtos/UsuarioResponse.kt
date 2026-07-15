package com.dtos

import com.database.model.ContaModel
import com.database.model.UsuarioModel
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.xml.crypto.Data

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
    val data: LocalDateTime = LocalDateTime.now(),
)
data class  BuscarChaveResponse(
    val usuario: UsuarioModel,
    val conta: ContaModel
)
data class ErroResponse(
    val mensagem: String
)
data class PagamentoResponse(
    val nomeFavorecido: String,
    val saldo: String?
)
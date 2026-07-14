package com.dtos

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
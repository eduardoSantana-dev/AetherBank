package com.example.aetherbankapp_eduardo.retrofit

import com.dtos.LoginRequest
import com.dtos.RegistroRequest

class Repository {
    suspend fun login(request: LoginRequest) = RetrofitClient.api.login(request)

    suspend fun registrar(request: RegistroRequest)= RetrofitClient.api.registrar(request)

    suspend fun verSaldo(token: String) = RetrofitClient.api.verSaldo(token)
    suspend fun validarToken(token: String) = RetrofitClient.api.validarToken(token)
}
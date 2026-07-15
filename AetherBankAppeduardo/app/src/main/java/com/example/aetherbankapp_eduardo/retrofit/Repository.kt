package com.example.aetherbankapp_eduardo.retrofit

import com.dtos.LoginRequest
import com.dtos.RegistroRequest
import com.dtos.TransferenciaRequest

class Repository {
    suspend fun login(request: LoginRequest) = RetrofitClient.api.login(request)

    suspend fun registrar(request: RegistroRequest)= RetrofitClient.api.registrar(request)

    suspend fun verSaldo(token: String) = RetrofitClient.api.verSaldo(token)
    suspend fun validarToken(token: String) = RetrofitClient.api.validarToken(token)

    suspend fun infoPagar(token: String,chave: String) = RetrofitClient.api.infoPagar(token, chave)

    suspend fun enviarTrans(request: TransferenciaRequest,token: String)= RetrofitClient.api.enviarTrans(token,request)

}
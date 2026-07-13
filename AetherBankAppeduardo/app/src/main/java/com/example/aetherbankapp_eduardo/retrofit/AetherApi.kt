package com.example.aetherbankapp_eduardo.retrofit

import com.dtos.CredenciaisResponse
import com.dtos.LoginRequest
import com.dtos.LoginResponse
import com.dtos.RegistroRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AetherApi {
    @POST("/usuarios/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST("/usuarios/auth/registrar")
    suspend fun registrar(
        @Body request: RegistroRequest
    ): Response<CredenciaisResponse>

}
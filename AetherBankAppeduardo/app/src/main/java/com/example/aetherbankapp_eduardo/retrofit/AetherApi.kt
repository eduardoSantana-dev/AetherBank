package com.example.aetherbankapp_eduardo.retrofit

import com.dtos.ContaResponse
import com.dtos.CredenciaisResponse
import com.dtos.LoginRequest
import com.dtos.LoginResponse
import com.dtos.PagamentoResponse
import com.dtos.RegistroRequest
import com.dtos.TransferenciaRequest
import com.dtos.TransferenciaResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AetherApi {

    @GET("/usuarios/validar")
    suspend fun validarToken(
        @Header("Authorization") token : String
    ): Response <Void>
    @POST("/usuarios/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST("/usuarios/auth/registrar")
    suspend fun registrar(
        @Body request: RegistroRequest
    ): Response<CredenciaisResponse>

    @GET("/conta/verSaldo")
    suspend fun verSaldo(
        @Header("Authorization") token : String
    ): Response <ContaResponse>

    @GET("conta/infosPagar/{chave}")
    suspend fun infoPagar(
        @Header("Authorization") token : String,
        @Path("chave") chave: String
    ): Response <PagamentoResponse>
   @POST("transferencia/enviar")
   suspend fun enviarTrans(
       @Header("Authorization") token : String,
       @Body request: TransferenciaRequest
   ): Response <TransferenciaResponse>
}